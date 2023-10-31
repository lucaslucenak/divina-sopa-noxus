package com.lucalucenak.Noxus.exceptions;

public class AlreadyExistentActiveCashRegisterBalanceException extends RuntimeException {
    public AlreadyExistentActiveCashRegisterBalanceException(String msg) {
        super(msg);
    }
}