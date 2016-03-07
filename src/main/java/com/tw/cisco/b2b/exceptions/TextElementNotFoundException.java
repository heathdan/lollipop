package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 03/03/16.
 */
public class TextElementNotFoundException extends GenericException {

    public TextElementNotFoundException() {
    }

    public TextElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextElementNotFoundException(String message) {
        super(message);
    }

    public TextElementNotFoundException(Throwable cause) {
        super(cause);
    }
}
