package com.token.exception;

public class JwtTokenAuthenticationFilterException extends BaseException {
    public JwtTokenAuthenticationFilterException(String msg){
        super(msg);
    }
}
