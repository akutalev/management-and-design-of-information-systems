package com.management_and_design_of_information_systems.uobject;

public class ReadValueException extends Exception {

    public ReadValueException() {
        super();
    }

    public ReadValueException(final String message) {
        super(message);
    }

    public ReadValueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReadValueException(final Throwable cause) {
        super(cause);
    }
}
