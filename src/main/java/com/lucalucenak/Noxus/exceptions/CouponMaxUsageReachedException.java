package com.lucalucenak.Noxus.exceptions;

public class CouponMaxUsageReachedException extends RuntimeException {
    public CouponMaxUsageReachedException(String msg) {
        super(msg);
    }
}
