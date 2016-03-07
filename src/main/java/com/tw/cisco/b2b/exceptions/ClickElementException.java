package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 03/03/16.
 */
public class ClickElementException extends GenericException {
    public ClickElementException() {
    }

    public ClickElementException(String message) {
        super(message);
    }

    public ClickElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClickElementException(Throwable cause) {
        super(cause);
    }
}
