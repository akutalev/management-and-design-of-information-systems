package com.management_and_design_of_information_systems.uobject;

public class WriteValueException extends Exception {

    public WriteValueException() {
        super();
    }

    public WriteValueException(final String message) {
        super(message);
    }

    public WriteValueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WriteValueException(final Throwable cause) {
        super(cause);
    }
}
