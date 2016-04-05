package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 04/04/16.
 */
public class ElementNotVisibleInUI extends GenericException {

    public ElementNotVisibleInUI() {
    }

    public ElementNotVisibleInUI(String message) {
        super(message);
    }

    public ElementNotVisibleInUI(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotVisibleInUI(Throwable cause) {
        super(cause);
    }
}
