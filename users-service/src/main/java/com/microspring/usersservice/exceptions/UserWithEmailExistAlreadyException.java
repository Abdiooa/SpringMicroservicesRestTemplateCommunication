package com.microspring.usersservice.exceptions;

public class UserWithEmailExistAlreadyException extends RuntimeException{
    private String message;
    public UserWithEmailExistAlreadyException(){

    }
    public UserWithEmailExistAlreadyException(String message){
        super(message);
        this.message = message;
    }
}
