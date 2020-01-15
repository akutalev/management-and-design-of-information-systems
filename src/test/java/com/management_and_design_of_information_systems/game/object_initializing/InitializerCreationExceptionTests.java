package com.management_and_design_of_information_systems.game.object_initializing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InitializerCreationExceptionTests {

    @Test(expected = InitializerCreationException.class)
    public void checkInitializerCreationException_checkDefaultConstructor() throws Exception {
        throw new InitializerCreationException();
    }

    @Test
    public void checkInitializerCreationException_checkConstructorWithMessage() throws Exception {
        try{
            throw new InitializerCreationException("message");
        } catch (InitializerCreationException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void checkInitializerCreationException_checkConstructorWithThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new InitializerCreationException(ex);
        } catch (InitializerCreationException e) {
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void checkInitializerCreationException_checkConstructorWithMessageAndThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new InitializerCreationException("message", ex);
        } catch (InitializerCreationException e) {
            assertEquals(ex, e.getCause());
            assertEquals("message", e.getMessage());
        }
    }
}
