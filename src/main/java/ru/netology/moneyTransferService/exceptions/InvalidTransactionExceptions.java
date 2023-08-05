package ru.netology.moneyTransferService.exceptions;

public class InvalidTransactionExceptions extends RuntimeException {
    public InvalidTransactionExceptions(String message) {
        super(message);
    }
}
