package ru.netology.moneyTransferService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneyTransferService.exceptions.CardIsExpiredException;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.exceptions.InvalidTransactionExceptions;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(CardIsExpiredException.class)
    public ResponseEntity<String> CardIsExpiredUserHandler(CardIsExpiredException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<String> InputDataHandler(InputDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTransactionExceptions.class)
    public ResponseEntity<String> InvalidTransactionHandler(InvalidTransactionExceptions e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
