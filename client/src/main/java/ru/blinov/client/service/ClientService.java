package ru.blinov.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.blinov.client.entity.Client;
import ru.blinov.client.exception.NotFoundException;
import ru.blinov.client.mapper.ClientMapper;
import ru.blinov.client.repository.ClientRepository;
import ru.blinov.client.web.request.ClientRegistrationRequest;
import ru.blinov.client.web.request.ClientUpdateRequest;
import ru.blinov.client.web.response.ClientResponse;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientResponse registerClient(ClientRegistrationRequest clientRegistrationRequest) {
        Client client = clientMapper.clientRegistrationRequestToClient(clientRegistrationRequest);
        log.info("Mapped client registration request: {}", client);
        Client registeredClient = clientRepository.save(client);
        log.info("Registered client: {}", registeredClient);
        return clientMapper.clientToClientResponse(registeredClient);
    }

    public ClientResponse updateClient(ClientUpdateRequest clientUpdateRequest) {
        getClientById(clientUpdateRequest.getId());
        Client client = clientMapper.clientUpdateRequestToClient(clientUpdateRequest);
        log.info("Mapped client update request: {}", client);
        Client updatedClient = clientRepository.save(client);
        log.info("Updated client: {}", updatedClient);
        return clientMapper.clientToClientResponse(updatedClient);
    }

    public List<ClientResponse> getClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::clientToClientResponse)
                .peek(clientResponse -> log.info("Mapped client: {}", clientResponse))
                .toList();
    }

    public ClientResponse getClientById(Long clientId) {
        Client extractedClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client with ID '" + clientId + "' is not found"));
        log.info("Extracted client: {}", extractedClient);
        return clientMapper.clientToClientResponse(extractedClient);
    }

    public void deleteClientById(Long clientId) {
        getClientById(clientId);
        clientRepository.deleteById(clientId);
        log.info("Client deleted; client ID: {}", clientId);
    }
}
