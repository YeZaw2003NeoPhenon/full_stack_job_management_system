package com.example.job_management_system.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
