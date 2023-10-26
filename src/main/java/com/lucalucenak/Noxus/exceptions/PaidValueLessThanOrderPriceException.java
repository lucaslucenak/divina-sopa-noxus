package com.lucalucenak.Noxus.exceptions;

public class PaidValueLessThanOrderPriceException extends RuntimeException {

    public PaidValueLessThanOrderPriceException(String msg) {
        super(msg);
    }
}
