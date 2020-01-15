package com.management_and_design_of_information_systems.game.object_initializing;

public class InitializerCreationException extends Exception {

    public InitializerCreationException() {
        super();
    }

    public InitializerCreationException(final Throwable cause) {
        super(cause);
    }

    public InitializerCreationException(final String message) {
        super(message);
    }

    public InitializerCreationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
