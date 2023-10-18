package com.lucalucenak.Noxus.exceptions;

public class DeliveryAlreadyUsedAtAnotherOrderException extends RuntimeException {

    public DeliveryAlreadyUsedAtAnotherOrderException(String msg) {
        super(msg);
    }
}