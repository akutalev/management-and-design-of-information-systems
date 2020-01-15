package com.management_and_design_of_information_systems.command;

import com.management_and_design_of_information_systems.base.CreationException;
import com.management_and_design_of_information_systems.uobject.UObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommandToListTests {

    @Test
    public void checkObjectCreation() throws Exception {
        Command innerCommand = mock(Command.class);
        List<UObject> objects = new ArrayList<>();
        objects.add(mock(UObject.class));
        Command command = new CommandToList(innerCommand, objects);

        assertNotNull(command);
    }

    @Test
    public void checkObjectCreationWithNotInitializedArgument() throws Exception {
        Command innerCommand = mock(Command.class);
        List<UObject> objects = new ArrayList<>();
        objects.add(mock(UObject.class));
        try {
            new CommandToList(null, null);
            fail();
        } catch (CreationException e) {
            // success
        }
        try {
            new CommandToList(null, objects);
            fail();
        } catch (CreationException e) {
            // success
        }
        try {
            new CommandToList(innerCommand, null);
            fail();
        } catch (CreationException e) {
            // success
        }
        try {
            new CommandToList(innerCommand, new ArrayList<>());
            fail();
        } catch (CreationException e) {
            // success
        }
    }

    @Test
    public void checkSuccessMacroCommandExecution()
            throws Exception {
        Command innerCommand = mock(Command.class);
        List<UObject> objects = new ArrayList<>();
        UObject object1 = mock(UObject.class);
        UObject object2 = mock(UObject.class);
        objects.add(object1);
        objects.add(object2);
        Command command = new CommandToList(innerCommand, objects);
        command.execute();

        verify(innerCommand, times(1)).execute(object1);
        verify(innerCommand, times(1)).execute(object2);
    }

    @Test (expected = CommandExecutionException.class)
    public void checkExceptionInInnerCommandExecution()
            throws Exception {
        Command innerCommand = mock(Command.class);
        List<UObject> objects = new ArrayList<>();
        UObject object1 = mock(UObject.class);
        UObject object2 = mock(UObject.class);
        objects.add(object1);
        objects.add(object2);
        doThrow(Exception.class).when(innerCommand).execute(object2);
        Command command = new CommandToList(innerCommand, objects);
        command.execute();
    }
}
