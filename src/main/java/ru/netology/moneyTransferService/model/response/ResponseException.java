package ru.netology.moneyTransferService.model.response;

public class ResponseException {
    private final String message;
    private final int id = 0;

    public ResponseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
