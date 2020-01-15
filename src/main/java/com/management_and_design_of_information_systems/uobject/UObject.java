package com.management_and_design_of_information_systems.uobject;

public interface UObject {

    <T> T getValue(String name) throws ReadValueException;

    void setValue(String name, Object value) throws WriteValueException;
}
