package com.lucalucenak.Noxus.exceptions;

public class CouponNotAvailableException extends RuntimeException {
    public CouponNotAvailableException(String msg) {
        super(msg);
    }
}