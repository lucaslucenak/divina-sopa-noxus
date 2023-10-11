package com.lucalucenak.Noxus.exceptions;

public class AddressNotBelongingToClientAccountException extends RuntimeException {

    public AddressNotBelongingToClientAccountException(String msg) {
        super(msg);
    }
}
