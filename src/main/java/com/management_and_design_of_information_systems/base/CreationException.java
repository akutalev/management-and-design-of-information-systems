package com.management_and_design_of_information_systems.base;

public class CreationException extends Exception {

    public CreationException() {
        super();
    }

    public CreationException(final Throwable cause) {
        super(cause);
    }

    public CreationException(final String message) {
        super(message);
    }

    public CreationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
