package ru.netology.moneyTransferService.service;

import ru.netology.moneyTransferService.model.DTO.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.DTO.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.DTO.response.Response;

public interface MoneyTransferService {
    Response transferMoney(RequestForMoneyTransfer moneyTransferOperation);

    Response confirmOperation(RequestForConfirmOperation requestForConfirmOperation);
}
