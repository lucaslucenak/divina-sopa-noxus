package com.lucalucenak.Noxus.exceptions;

public class AlreadyUsedCpfException extends RuntimeException {
    public AlreadyUsedCpfException(String msg) {
        super(msg);
    }
}