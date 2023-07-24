package com.microspring.departementservice.exceptions;

public class DepartmentWithThatNameAlreadyExist extends RuntimeException{
    private String message;

    public DepartmentWithThatNameAlreadyExist() {
    }
    public DepartmentWithThatNameAlreadyExist(String message){
        super(message);
        this.message = message;
    }
}
