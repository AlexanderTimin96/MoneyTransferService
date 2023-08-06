package ru.netology.moneyTransferService.model.response;

import java.util.Objects;

public record ResponseTransfer(String operationId) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseTransfer that = (ResponseTransfer) o;
        return Objects.equals(operationId, that.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }
}
