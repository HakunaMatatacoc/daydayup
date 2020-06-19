package com.jianli;

import jdk.nashorn.internal.ir.CaseNode;

public class LoginFailedException extends BaseException{
    public LoginFailedException(){
        super();
    }

    public LoginFailedException(String message){
        super(message);
    }

    public LoginFailedException(String message,Throwable cause){
        super(message, cause);
    }

    public LoginFailedException(Throwable cause){
        super(cause);
    }
}
