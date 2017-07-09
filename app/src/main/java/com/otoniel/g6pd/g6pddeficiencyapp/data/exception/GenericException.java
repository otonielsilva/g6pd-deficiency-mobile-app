package com.otoniel.g6pd.g6pddeficiencyapp.data.exception;

/**
 * Created by eltonjhony on 09/07/17.
 */

public class GenericException extends Exception {

    private String message;

    public GenericException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
