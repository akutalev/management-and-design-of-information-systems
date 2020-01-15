package com.management_and_design_of_information_systems.game.object_initializing;

import com.management_and_design_of_information_systems.base.CreationException;
import com.management_and_design_of_information_systems.command.Command;
import com.management_and_design_of_information_systems.command.CommandToList;
import com.management_and_design_of_information_systems.command.MacroCommand;
import com.management_and_design_of_information_systems.ioc.IOC;
import com.management_and_design_of_information_systems.ioc.ResolutionException;
import com.management_and_design_of_information_systems.uobject.UObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameObjectInitializerCommandCreator implements InitializerCreator {

    private IOC IoC;

    public GameObjectInitializerCommandCreator(
            final IOC IoC
    ) throws CreationException {
        if (null == IoC) {
            throw new CreationException();
        }
        this.IoC = IoC;
    }

    public void createInitializer() throws InitializerCreationException {
        try {
            Iterator<String> objectTypes = IoC.resolve("get object types");
            while(objectTypes.hasNext()) {
                String objectType = objectTypes.next();
                IoC.resolve(
                        "registry",
                        objectType + ":initialize-command",
                        createCommand(objectType)
                );
            }
        } catch (Exception e) {
            throw new InitializerCreationException(e);
        }
    }

    private Command createCommand(final String objectType)
            throws Exception {
        List<Command> commands = new ArrayList<>();
        try {
            Iterator<String> objectTypeRules = IoC.resolve(
                    "get rules for given object type",
                    objectType
            );
            while (objectTypeRules.hasNext()) {
                String objectTypeRule = objectTypeRules.next();
                Iterator<String> objectParameters = IoC.resolve(
                        "get object properties by given type and rule",
                        objectType,
                        objectTypeRule
                );
                while (objectParameters.hasNext()) {
                    String objectParameter = objectParameters.next();
                    Command command = IoC.resolve(
                            objectParameter
                    );
                    if (null != command) {
                        commands.add(command);
                    }
                }
            }
            Command macroCommandForOneGameObject = new MacroCommand(commands);
            List<UObject> gameObjects = IoC.resolve(
                    "get objects by given type",
                    objectType
            );
            return new CommandToList(
                    macroCommandForOneGameObject,
                    gameObjects
            );
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
