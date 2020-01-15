package com.management_and_design_of_information_systems.command;

public interface Command {

    void execute(Object ... objects) throws CommandExecutionException;
}
