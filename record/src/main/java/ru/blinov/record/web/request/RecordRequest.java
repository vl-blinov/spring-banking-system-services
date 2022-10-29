package ru.blinov.record.web.request;

import lombok.Data;

@Data
public class RecordRequest {

    private Long accId;

    private String accNum;

    private String operName;
}
