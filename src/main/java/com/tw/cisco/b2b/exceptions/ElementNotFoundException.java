package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 04/03/16.
 */
public class ElementNotFoundException extends GenericException {
    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }
}
