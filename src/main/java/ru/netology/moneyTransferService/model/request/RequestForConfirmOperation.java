package ru.netology.moneyTransferService.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RequestForConfirmOperation {
    @NotBlank
    private final String operationId;
    @NotBlank
    @Size(min = 4, max = 4)
    private final String code;

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
