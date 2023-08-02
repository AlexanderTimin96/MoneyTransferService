package ru.netology.moneyTransferService.exceptions;

public class CardIsExpiredException extends RuntimeException{
    public CardIsExpiredException(String message) {
        super(message);
    }
}
