package ru.blinov.client.web.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientResponse {

    private Long id;

    private String fullName;

    private String placeOfBirth;

    private LocalDate dateOfBirth;

    private String address;

    private String passport;

    private List<Long> accountsIdList;
}
