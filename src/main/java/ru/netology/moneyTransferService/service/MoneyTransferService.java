package ru.netology.moneyTransferService.service;

import ru.netology.moneyTransferService.model.requestObject.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.requestObject.RequestForMoneyTransfer;

public interface MoneyTransferService {
    String transferMoney(RequestForMoneyTransfer moneyTransferOperation);

    String confirmOperation(RequestForConfirmOperation requestForConfirmOperation);
}
