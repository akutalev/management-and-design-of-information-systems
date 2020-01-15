package com.management_and_design_of_information_systems.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandExecutionExceptionTests {

    @Test(expected = CommandExecutionException.class)
    public void checkCommandExecutionException_checkDefaultConstructor() throws Exception {
        throw new CommandExecutionException();
    }

    @Test
    public void checkCommandExecutionException_checkConstructorWithMessage() throws Exception {
        try{
            throw new CommandExecutionException("message");
        } catch (CommandExecutionException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void checkCommandExecutionException_checkConstructorWithThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new CommandExecutionException(ex);
        } catch (CommandExecutionException e) {
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void checkCommandExecutionException_checkConstructorWithMessageAndThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new CommandExecutionException("message", ex);
        } catch (CommandExecutionException e) {
            assertEquals(ex, e.getCause());
            assertEquals("message", e.getMessage());
        }
    }
}
