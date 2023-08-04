package ru.netology.moneyTransferService.service;

import ru.netology.moneyTransferService.model.response.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.response.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.response.ResponseTransfer;

public interface MoneyTransferService {
    ResponseTransfer transferMoney(RequestForMoneyTransfer moneyTransferOperation);

    ResponseTransfer confirmOperation(RequestForConfirmOperation requestForConfirmOperation);
}
