package com.projects.sunrise_hotel.exception;

public class RoleAlreadyExistException extends RuntimeException{
    public RoleAlreadyExistException(String message){
        super(message);
    }
}
