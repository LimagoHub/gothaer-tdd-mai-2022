package de.gothaer.services;

public class PersoneServiceException extends Exception {

    public PersoneServiceException() {
    }

    public PersoneServiceException(String message) {
        super(message);
    }

    public PersoneServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersoneServiceException(Throwable cause) {
        super(cause);
    }

    public PersoneServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
