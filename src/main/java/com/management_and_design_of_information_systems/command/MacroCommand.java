package com.management_and_design_of_information_systems.command;

import com.management_and_design_of_information_systems.base.CreationException;

import java.util.List;

public class MacroCommand implements Command {

    private List<Command> commands;

    public MacroCommand(final List<Command> commands) throws CreationException {
        if (null == commands || commands.isEmpty()) {
            throw  new CreationException();
        }
        this.commands = commands;
    }

    @Override
    public void execute(Object... objects) throws CommandExecutionException {
        try {
            for (Command command : this.commands) {
                command.execute(objects);
            }
        } catch (Exception e) {
            throw new CommandExecutionException(e);
        }
    }
}
