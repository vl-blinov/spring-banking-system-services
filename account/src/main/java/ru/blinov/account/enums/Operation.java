package ru.blinov.account.enums;

public enum Operation {

    OPEN("Open account"),
    CLOSE("Close account");

    private final String message;

    Operation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
