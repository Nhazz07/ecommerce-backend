package com.example.animeecommercebackend.Exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String message){
        super(message);
    }
}
