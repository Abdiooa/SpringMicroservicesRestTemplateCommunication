package com.microspring.departementservice.exceptions;

public class DepartementNotFoundExceptiom extends RuntimeException{
    private String message;

    public  DepartementNotFoundExceptiom(){

    }
    public DepartementNotFoundExceptiom(String message){
        super(message);
        this.message = message;
    }
}
