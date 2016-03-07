package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 28/02/16.
 */
public class GenericException extends Exception {

    public GenericException() {

    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, Throwable cause) {

        super(message, cause);
    }

    public GenericException(Throwable cause) {
        super(cause);
    }

}
