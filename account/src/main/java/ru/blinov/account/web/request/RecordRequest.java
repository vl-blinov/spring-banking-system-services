package ru.blinov.account.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordRequest {

    private Long accId;

    private String accNum;

    private String operName;
}
