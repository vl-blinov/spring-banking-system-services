package ru.blinov.record.web.handler;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

@NoArgsConstructor
public class ExceptionHandlerUtils {

    public static String buildErrorMessage(Throwable t) {
        StringBuilder message =
                new StringBuilder(ExceptionUtils.getMessage(t));
        Throwable cause = t.getCause();
        if (cause != null) {
            message.append(", cause: ").append(ExceptionUtils.getMessage(cause));
        }
        return message.toString();
    }
}
