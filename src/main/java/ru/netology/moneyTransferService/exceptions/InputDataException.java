package ru.netology.moneyTransferService.exceptions;

public class InputDataException extends RuntimeException {
    public InputDataException(String message) {
        super(message);
    }
}
