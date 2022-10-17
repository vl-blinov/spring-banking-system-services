package ru.blinov.client.mapper;

import org.mapstruct.Mapper;
import ru.blinov.client.entity.Client;
import ru.blinov.client.web.request.ClientRegistrationRequest;
import ru.blinov.client.web.request.ClientUpdateRequest;
import ru.blinov.client.web.response.ClientResponse;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client clientRegistrationRequestToClient(ClientRegistrationRequest clientRegistrationRequest);

    Client clientUpdateRequestToClient(ClientUpdateRequest clientUpdateRequest);

    ClientResponse clientToClientResponse(Client client);
}
