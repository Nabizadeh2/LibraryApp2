package com.nj.libraryapp2.exception;

public class RentNotFoundException extends RuntimeException{
    public RentNotFoundException(String message){
        super(message);
    }
}
