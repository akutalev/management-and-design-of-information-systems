package com.management_and_design_of_information_systems.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreationExceptionTests {

    @Test(expected = CreationException.class)
    public void checkCreationException_checkDefaultConstructor() throws Exception {
        throw new CreationException();
    }

    @Test
    public void checkCreationException_checkConstructorWithMessage() throws Exception {
        try{
            throw new CreationException("message");
        } catch (CreationException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void checkCreationException_checkConstructorWithThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new CreationException(ex);
        } catch (CreationException e) {
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void checkCreationException_checkConstructorWithMessageAndThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new CreationException("message", ex);
        } catch (CreationException e) {
            assertEquals(ex, e.getCause());
            assertEquals("message", e.getMessage());
        }
    }
}
