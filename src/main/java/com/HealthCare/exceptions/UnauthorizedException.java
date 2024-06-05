package com.HealthCare.exceptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String error) {
        super(error);
    }

}