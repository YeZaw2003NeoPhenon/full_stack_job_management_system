package com.example.job_management_system.exception;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String message) {
        super(message);
    }
}
