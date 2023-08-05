package ru.netology.moneyTransferService.logger;

import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;

public interface TransferLogger {
    void createFileLog();

    void logSuccessfulOperation(TransferOperation operation);

    void logInvalidRequest(RequestForMoneyTransfer requestForMoneyTransfer, String msg);

    void logOperationCancel(TransferOperation operation, String msg);
}
