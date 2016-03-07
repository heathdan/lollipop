package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 04/03/16.
 */
public class ClickIconNotFoundException extends GenericException {
    public ClickIconNotFoundException() {
    }

    public ClickIconNotFoundException(String message) {
        super(message);
    }

    public ClickIconNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClickIconNotFoundException(Throwable cause) {
        super(cause);
    }
}
