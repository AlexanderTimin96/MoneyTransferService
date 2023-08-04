package ru.netology.moneyTransferService.model.response;

public class ResponseTransfer {
    private String operationId;

    public ResponseTransfer(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
