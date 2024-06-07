package com.HealthCare.exceptions;

public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String error) {
        super(error);
    }
}
