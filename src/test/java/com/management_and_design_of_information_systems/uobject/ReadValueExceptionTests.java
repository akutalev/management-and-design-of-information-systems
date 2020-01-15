package com.management_and_design_of_information_systems.uobject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadValueExceptionTests {

    @Test(expected = ReadValueException.class)
    public void checkReadValueException_checkDefaultConstructor() throws Exception {
        throw new ReadValueException();
    }

    @Test
    public void checkReadValueException_checkConstructorWithMessage() throws Exception {
        try{
            throw new ReadValueException("message");
        } catch (ReadValueException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void checkReadValueException_checkConstructorWithThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new ReadValueException(ex);
        } catch (ReadValueException e) {
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void checkReadValueException_checkConstructorWithMessageAndThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new ReadValueException("message", ex);
        } catch (ReadValueException e) {
            assertEquals(ex, e.getCause());
            assertEquals("message", e.getMessage());
        }
    }
}
