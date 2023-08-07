package ru.netology.moneyTransferService.model.DTO.response;

public class ResponseException {
    private final String message;
    private final int id;

    public ResponseException(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
