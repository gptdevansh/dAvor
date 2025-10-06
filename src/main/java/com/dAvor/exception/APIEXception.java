package com.dAvor.exception;

public class APIEXception extends RuntimeException{

    public APIEXception() {
    }

    public APIEXception(String message) {
        super(message);
    }
}
