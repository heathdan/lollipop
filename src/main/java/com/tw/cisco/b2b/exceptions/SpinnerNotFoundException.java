package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 04/03/16.
 */
public class SpinnerNotFoundException extends GenericException {
    public SpinnerNotFoundException() {
    }

    public SpinnerNotFoundException(String message) {
        super(message);
    }

    public SpinnerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpinnerNotFoundException(Throwable cause) {
        super(cause);
    }
}
