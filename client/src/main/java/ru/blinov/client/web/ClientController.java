package ru.blinov.client.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blinov.client.service.ClientService;
import ru.blinov.client.web.constant.WebConstant;
import ru.blinov.client.web.request.ClientRegistrationRequest;
import ru.blinov.client.web.request.ClientUpdateRequest;
import ru.blinov.client.web.response.ClientResponse;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = WebConstant.VERSION_URL + "/clients",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        log.info("Client registration request: {}", clientRegistrationRequest);
        ClientResponse clientResponse = clientService.registerClient(clientRegistrationRequest);
        log.info("Response with registered client: {}", clientResponse);
        return ResponseEntity.ok(clientResponse);
    }

    @PutMapping
    public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientUpdateRequest clientUpdateRequest) {
        log.info("Client update request: {}", clientUpdateRequest);
        ClientResponse clientResponse = clientService.updateClient(clientUpdateRequest);
        log.info("Response with updated client: {}", clientResponse);
        return ResponseEntity.ok(clientResponse);
    }

    @GetMapping
    public List<ClientResponse> getClients() {
        log.info("Get list of clients request");
        List<ClientResponse> clientResponseList = clientService.getClients();
        log.info("Client response list; number of clients in the list: {}", clientResponseList.size());
        return clientResponseList;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long clientId) {
        log.info("Get client by ID request; client ID: {}", clientId);
        ClientResponse clientResponse = clientService.getClientById(clientId);
        log.info("Response with extracted client: {}", clientResponse);
        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable Long clientId) {
        log.info("Delete client by ID request; client ID: {}", clientId);
        clientService.deleteClientById(clientId);
    }
}
