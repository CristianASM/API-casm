package com.api.casm.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class EmptyListException extends RuntimeException{

    public EmptyListException(String message){
        super(message);
    }
}
