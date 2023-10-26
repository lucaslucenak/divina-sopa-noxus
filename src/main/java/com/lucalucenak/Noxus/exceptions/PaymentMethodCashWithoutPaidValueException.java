package com.lucalucenak.Noxus.exceptions;

public class PaymentMethodCashWithoutPaidValueException extends RuntimeException {

    public PaymentMethodCashWithoutPaidValueException(String msg) {
        super(msg);
    }
}
