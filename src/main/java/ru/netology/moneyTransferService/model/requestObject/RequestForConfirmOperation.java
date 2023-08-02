package ru.netology.moneyTransferService.model.requestObject;

public class RequestForConfirmOperation {
    private String operationId;
    private String code;

    public RequestForConfirmOperation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }
}
