package com.management_and_design_of_information_systems.command;

import com.management_and_design_of_information_systems.base.CreationException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MacroCommandTests {

    @Test
    public void checkObjectCreation() throws Exception {
        List<Command> commands = new ArrayList<>();
        commands.add(mock(Command.class));
        Command command = new MacroCommand(commands);

        assertNotNull(command);
    }

    @Test
    public void checkObjectCreationWithNotInitializedArgument() throws Exception {
        try {
            new MacroCommand(null);
            fail();
        } catch (CreationException e) {
            // success
        }
        try {
            new MacroCommand(new ArrayList<>());
            fail();
        } catch (CreationException e) {
            // success
        }
    }

    @Test
    public void checkSuccessMacroCommandExecution()
            throws Exception {
        List<Command> commands = new ArrayList<>();
        Command innerCommand1 = mock(Command.class);
        Command innerCommand2 = mock(Command.class);
        commands.add(innerCommand1);
        commands.add(innerCommand2);
        Command macroCommand = new MacroCommand(commands);
        Object arg = mock(Object.class);
        macroCommand.execute(arg);

        verify(innerCommand1, times(1)).execute(arg);
        verify(innerCommand2, times(1)).execute(arg);
    }

    @Test (expected = CommandExecutionException.class)
    public void checkExceptionInInnerCommandExecution()
            throws Exception {
        List<Command> commands = new ArrayList<>();
        Command innerCommand1 = mock(Command.class);
        Command innerCommand2 = mock(Command.class);
        commands.add(innerCommand1);
        commands.add(innerCommand2);
        Command macroCommand = new MacroCommand(commands);
        Object arg = mock(Object.class);
        doThrow(Exception.class).when(innerCommand2).execute(arg);
        macroCommand.execute(arg);
    }
}
