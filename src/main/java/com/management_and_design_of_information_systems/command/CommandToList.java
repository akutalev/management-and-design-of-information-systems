package com.management_and_design_of_information_systems.command;

import com.management_and_design_of_information_systems.base.CreationException;
import com.management_and_design_of_information_systems.uobject.UObject;

import java.util.List;

public class CommandToList implements Command {

    private Command command;
    private List<UObject> objects;

    public CommandToList(final Command command, final List<UObject> objects) throws CreationException {
        if (null == command || null == objects || objects.isEmpty()) {
            throw new CreationException();
        }
        this.command = command;
        this.objects = objects;
    }

    @Override
    public void execute(Object... objects)
            throws CommandExecutionException {
        try {
            for (UObject object : this.objects) {
                this.command.execute(object);
            }
        } catch (Exception e){
            throw new CommandExecutionException(e);
        }
    }
}
