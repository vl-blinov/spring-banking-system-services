package ru.blinov.client.web.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientUpdateRequest {

    private Long id;

    private String fullName;

    private String placeOfBirth;

    private LocalDate dateOfBirth;

    private String address;

    private String passport;
}
