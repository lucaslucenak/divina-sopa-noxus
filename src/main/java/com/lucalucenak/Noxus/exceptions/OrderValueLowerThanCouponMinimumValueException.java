package com.lucalucenak.Noxus.exceptions;

public class OrderValueLowerThanCouponMinimumValueException extends RuntimeException {

    public OrderValueLowerThanCouponMinimumValueException(String msg) {
        super(msg);
    }
}
