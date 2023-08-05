package ru.netology.moneyTransferService.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.exceptions.InvalidTransactionExceptions;
import ru.netology.moneyTransferService.model.response.ResponseException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ResponseException> handleInputDataHandler(InputDataException e) {
        return ResponseEntity.badRequest().body(new ResponseException(e.getMessage()));
    }

    @ExceptionHandler(InvalidTransactionExceptions.class)
    public ResponseEntity<ResponseException> handleInvalidTransactionHandler(InvalidTransactionExceptions e) {
        return ResponseEntity.badRequest().body(new ResponseException(e.getMessage()));
    }
}
