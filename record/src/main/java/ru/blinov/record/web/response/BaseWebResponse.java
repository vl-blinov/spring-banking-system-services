package ru.blinov.record.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseWebResponse {
    private String errorMessage;
}
