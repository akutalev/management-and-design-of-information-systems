package com.management_and_design_of_information_systems.command;

public class CommandExecutionException extends Exception {

    public CommandExecutionException() {
        super();
    }

    public CommandExecutionException(final Throwable cause) {
        super(cause);
    }

    public CommandExecutionException(final String message) {
        super(message);
    }

    public CommandExecutionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
