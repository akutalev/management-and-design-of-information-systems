package com.management_and_design_of_information_systems.ioc;

public interface IOC {

    <T> T resolve(String name, Object ... args) throws ResolutionException;
}
