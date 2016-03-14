package com.tw.cisco.b2b.exceptions;

/**
 * Created by aswathyn on 13/03/16.
 */
public class CSVParsingException extends GenericException {

    public CSVParsingException() {
    }

    public CSVParsingException(String message) {
        super(message);
    }

    public CSVParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVParsingException(Throwable cause) {
        super(cause);
    }
}
