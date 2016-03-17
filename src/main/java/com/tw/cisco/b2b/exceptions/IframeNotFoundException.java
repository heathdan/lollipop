package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 17/03/16.
 */
public class IframeNotFoundException extends GenericException {

    public IframeNotFoundException() {
    }

    public IframeNotFoundException(String message) {
        super(message);
    }

    public IframeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IframeNotFoundException(Throwable cause) {
        super(cause);
    }
}
