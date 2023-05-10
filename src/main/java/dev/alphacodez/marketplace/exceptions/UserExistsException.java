package dev.alphacodez.marketplace.exceptions;

public class UserExistsException extends Exception{
    public UserExistsException(String errorMessage) {
        super(errorMessage);
    }
}
