package com.HealthCare.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(String error) {
        super(error);
    }

}
