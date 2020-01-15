package com.management_and_design_of_information_systems.ioc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResolutionExceptionTests {

    @Test(expected = ResolutionException.class)
    public void checkResolutionException_checkDefaultConstructor() throws Exception {
        throw new ResolutionException();
    }

    @Test
    public void checkResolutionException_checkConstructorWithMessage() throws Exception {
        try{
            throw new ResolutionException("message");
        } catch (ResolutionException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void checkResolutionException_checkConstructorWithThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new ResolutionException(ex);
        } catch (ResolutionException e) {
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void checkResolutionException_checkConstructorWithMessageAndThrowable() throws Exception {
        Exception ex = new Exception();
        try{
            throw new ResolutionException("message", ex);
        } catch (ResolutionException e) {
            assertEquals(ex, e.getCause());
            assertEquals("message", e.getMessage());
        }
    }
}
