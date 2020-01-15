package com.management_and_design_of_information_systems.ioc;

public class ResolutionException extends Exception {

    public ResolutionException() {
        super();
    }

    public ResolutionException(final String message) {
        super(message);
    }

    public ResolutionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResolutionException(final Throwable cause) {
        super(cause);
    }
}
