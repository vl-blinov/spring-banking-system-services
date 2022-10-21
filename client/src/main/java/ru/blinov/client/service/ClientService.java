package ru.blinov.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.blinov.client.entity.Client;
import ru.blinov.client.exception.NotFoundException;
import ru.blinov.client.mapper.ClientMapper;
import ru.blinov.client.repository.ClientRepository;
import ru.blinov.client.service.client.AccountFeignClient;
import ru.blinov.client.web.request.ClientRegistrationRequest;
import ru.blinov.client.web.request.ClientUpdateRequest;
import ru.blinov.client.web.response.AccountResponse;
import ru.blinov.client.web.response.ClientResponse;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private AccountFeignClient accountFeignClient;

    public ClientResponse registerClient(ClientRegistrationRequest clientRegistrationRequest) {
        Client client = clientMapper.clientRegistrationRequestToClient(clientRegistrationRequest);
        log.info("Mapped client registration request: {}", client);
        Client registeredClient = clientRepository.save(client);
        log.info("Registered client: {}", registeredClient);
        return clientMapper.clientToClientResponse(registeredClient);
    }

    public ClientResponse updateClient(ClientUpdateRequest clientUpdateRequest) {
        Long clientId = clientUpdateRequest.getId();
        getClientById(clientId);
        Client client = clientMapper.clientUpdateRequestToClient(clientUpdateRequest);
        log.info("Mapped client update request: {}", client);
        Client updatedClient = clientRepository.save(client);
        log.info("Updated client: {}", updatedClient);
        List<AccountResponse> accountResponseList = accountFeignClient.getAccountsByClientId(clientId);
        List<Long> accountsIdList = accountResponseList
                .stream()
                .map(AccountResponse::getId)
                .toList();
        log.info("List of accounts IDs: {} for client with ID: {}", accountsIdList, clientId);
        ClientResponse clientResponse = clientMapper.clientToClientResponse(updatedClient);
        clientResponse.setAccountsIdList(accountsIdList);
        log.info("Mapped client: {}", clientResponse);
        return clientResponse;
    }

    public List<ClientResponse> getClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::clientToClientResponse)
                .peek(clientResponse -> clientResponse
                        .setAccountsIdList(accountFeignClient.getAccountsByClientId(clientResponse.getId())
                                .stream()
                                .map(AccountResponse::getId)
                                .toList()))
                .peek(clientResponse -> log.info("Mapped client: {}", clientResponse))
                .toList();
    }

    public ClientResponse getClientById(Long clientId) {
        Client extractedClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client with ID '" + clientId + "' is not found"));
        log.info("Extracted client: {}", extractedClient);
        ClientResponse clientResponse = clientMapper.clientToClientResponse(extractedClient);
        log.info("Mapped client: {}", clientResponse);
        List<AccountResponse> accountResponseList = accountFeignClient.getAccountsByClientId(clientId);
        List<Long> accountsIdList = accountResponseList
                .stream()
                .map(AccountResponse::getId)
                .toList();
        log.info("List of accounts IDs: {} for client with ID: {}", accountsIdList, clientId);
        clientResponse.setAccountsIdList(accountsIdList);
        return clientResponse;
    }

    public void deleteClientById(Long clientId) {
        getClientById(clientId);
        clientRepository.deleteById(clientId);
        log.info("Client deleted; client ID: {}", clientId);
        accountFeignClient.deleteAccountsByClientId(clientId);
        log.info("Accounts for client ID: {} have been deleted", clientId);
    }
}
