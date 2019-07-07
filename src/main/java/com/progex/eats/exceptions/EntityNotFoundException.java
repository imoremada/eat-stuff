package com.progex.eats.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
