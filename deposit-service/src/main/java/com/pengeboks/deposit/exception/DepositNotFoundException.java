package com.pengeboks.deposit.exception;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message) {
        super(message);
    }
}
