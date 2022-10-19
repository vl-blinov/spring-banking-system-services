package ru.blinov.account.web.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountResponse {

    private Long id;

    private Long clientId;

    private String accNum;

    private BigDecimal saldo;

    private LocalDate openDate;

    private LocalDate closeDate;
}
