package ru.blinov.record.web.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordResponse {

    private Long id;

    private String accNum;

    private String operName;

    private LocalDate operDate;
}
