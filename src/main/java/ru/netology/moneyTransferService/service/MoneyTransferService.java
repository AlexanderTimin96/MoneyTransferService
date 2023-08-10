package ru.netology.moneyTransferService.service;

import ru.netology.moneyTransferService.model.DTO.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.DTO.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.DTO.response.ResponseOperation;

public interface MoneyTransferService {
    ResponseOperation transferMoney(RequestForMoneyTransfer moneyTransferOperation);

    ResponseOperation confirmOperation(RequestForConfirmOperation requestForConfirmOperation);
}
