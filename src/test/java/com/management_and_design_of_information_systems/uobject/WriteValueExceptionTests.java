package com.management_and_design_of_information_systems.uobject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WriteValueExceptionTests {

    @Test(expected = WriteValueException.class)
    public void checkWriteValueException_checkDefaultConstructor() throws Exception {
        throw new WriteValueException();
    }

    @Test
    public void checkWriteValueException_checkConstructorWithMessage() throws Exception {
        try{
            throw new WriteValueException("message");
        } catch (WriteValueException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void checkWriteValueException_checkConstructorWithThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new WriteValueException(ex);
        } catch (WriteValueException e) {
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void checkWriteValueException_checkConstructorWithMessageAndThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new WriteValueException("message", ex);
        } catch (WriteValueException e) {
            assertEquals(ex, e.getCause());
            assertEquals("message", e.getMessage());
        }
    }
}
