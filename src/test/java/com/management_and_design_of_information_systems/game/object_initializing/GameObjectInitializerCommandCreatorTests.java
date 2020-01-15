package com.management_and_design_of_information_systems.game.object_initializing;

import com.management_and_design_of_information_systems.base.CreationException;
import com.management_and_design_of_information_systems.command.Command;
import com.management_and_design_of_information_systems.ioc.IOC;
import com.management_and_design_of_information_systems.ioc.ResolutionException;
import com.management_and_design_of_information_systems.uobject.ReadValueException;
import com.management_and_design_of_information_systems.uobject.UObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameObjectInitializerCommandCreatorTests {

    private IOC ioc;

    @Before
    public void init() {
        this.ioc = mock(IOC.class);
    }

    @Test
    public void checkObjectCreation() throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);

        assertNotNull(creator);
    }

    @Test (expected = CreationException.class)
    public void checkObjectCreationWithNotInitializedIoC() throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(null);
        fail();
    }

    @Test
    public void checkSuccessCreationCommandRegistration()
            throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);
        Iterator objectTypes = mock(Iterator.class);
        String objectType1 = "type1";
        String objectType2 = "type2";
        when(this.ioc.resolve("get object types")).thenReturn(objectTypes);
        when(objectTypes.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(objectTypes.next()).thenReturn(objectType1).thenReturn(objectType2);
        Iterator objectType1Rules = mock(Iterator.class);
        String objectType1Rule1 = "type1Rule1";
        String objectType1Rule2 = "type1Rule2";
        Iterator objectType2Rules = mock(Iterator.class);
        String objectType2Rule1 = "type2Rule1";
        when(objectType1Rules.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(objectType2Rules.hasNext()).thenReturn(true).thenReturn(false);
        when(objectType1Rules.next()).thenReturn(objectType1Rule1).thenReturn(objectType1Rule2);
        when(objectType2Rules.next()).thenReturn(objectType2Rule1);
        when(this.ioc.resolve("get rules for given object type", objectType1)).thenReturn(objectType1Rules);
        when(this.ioc.resolve("get rules for given object type", objectType2)).thenReturn(objectType2Rules);

        Iterator parameters11 = mock(Iterator.class);
        Iterator parameters12 = mock(Iterator.class);
        Iterator parameters21 = mock(Iterator.class);
        when(parameters11.hasNext()).thenReturn(true).thenReturn(false);
        when(parameters12.hasNext()).thenReturn(true).thenReturn(false);
        when(parameters21.hasNext()).thenReturn(true).thenReturn(false);
        String param11 = "parameter11";
        String param12 = "parameter12";
        String param21 = "parameter21";
        when(parameters11.next()).thenReturn(param11);
        when(parameters12.next()).thenReturn(param12);
        when(parameters21.next()).thenReturn(param21);

        when(this.ioc.resolve("get object properties by given type and rule", objectType1, objectType1Rule1)).thenReturn(parameters11);
        when(this.ioc.resolve("get object properties by given type and rule", objectType1, objectType1Rule2)).thenReturn(parameters12);
        when(this.ioc.resolve("get object properties by given type and rule", objectType2, objectType2Rule1)).thenReturn(parameters21);

        Command commandForParam11 = mock(Command.class);
        Command commandForParam12 = mock(Command.class);
        Command commandForParam21 = mock(Command.class);
        when(this.ioc.resolve(param11)).thenReturn(commandForParam11);
        when(this.ioc.resolve(param12)).thenReturn(commandForParam12);
        when(this.ioc.resolve(param21)).thenReturn(commandForParam21);

        UObject gameObject1 = mock(UObject.class);
        UObject gameObject2 = mock(UObject.class);
        UObject gameObject3 = mock(UObject.class);
        List<UObject> gameObjectsType1 = new ArrayList<>();
        gameObjectsType1.add(gameObject1);
        gameObjectsType1.add(gameObject2);
        List<UObject> gameObjectsType2 = new ArrayList<>();
        gameObjectsType2.add(gameObject3);
        when(this.ioc.resolve("get objects by given type", objectType1)).thenReturn(gameObjectsType1);
        when(this.ioc.resolve("get objects by given type", objectType2)).thenReturn(gameObjectsType2);

        creator.createInitializer();

        verify(this.ioc, times(1)).resolve(eq("registry"), eq("type1:initialize-command"), any());
        verify(this.ioc, times(1)).resolve(eq("registry"), eq("type2:initialize-command"), any());

        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        verify(this.ioc, times(13)).resolve(any(), captor.capture());

        Command mc1 = (Command) captor.getAllValues().get(7);
        Command mc2 = (Command) captor.getAllValues().get(13);

        mc1.execute();
        mc2.execute();

        verify(commandForParam11, times(1)).execute(gameObject1);
        verify(commandForParam12, times(1)).execute(gameObject1);
        verify(commandForParam11, times(1)).execute(gameObject2);
        verify(commandForParam12, times(1)).execute(gameObject2);
        verify(commandForParam21, times(1)).execute(gameObject3);
    }

    @Test (expected = InitializerCreationException.class)
    public void checkCreationCommandOnInnerExceptionRegistration()
            throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);
        Iterator objectTypes = mock(Iterator.class);
        String objectType = "type";
        when(this.ioc.resolve("get object types")).thenReturn(objectTypes);
        when(objectTypes.hasNext()).thenReturn(true).thenReturn(false);
        when(objectTypes.next()).thenReturn(objectType);

        creator.createInitializer();
    }

    @Test (expected = InitializerCreationException.class)
    public void checkExceptionOnIoCResolveException() throws Exception {
        doThrow(ResolutionException.class).when(this.ioc).resolve(any());
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);
        creator.createInitializer();
    }

    @Test (expected = InitializerCreationException.class)
    public void checkExceptionOnUObjectReadValueException() throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);
        Iterator objectTypes = mock(Iterator.class);
        UObject objectType = mock(UObject.class);
        when(this.ioc.resolve("get object types")).thenReturn(objectTypes);
        when(objectTypes.hasNext()).thenReturn(true);
        when(objectTypes.next()).thenReturn(objectType);
        doThrow(ReadValueException.class).when(objectType).getValue("name");
        creator.createInitializer();
    }

    @Test (expected = InitializerCreationException.class)
    public void checkOnObjectTypesIsNotRegistered() throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);
        when(this.ioc.resolve("get object types")).thenReturn(null);
        creator.createInitializer();
    }

    @Test (expected = InitializerCreationException.class)
    public void checkOnIoCThrownRuntimeException() throws Exception {
        InitializerCreator creator = new GameObjectInitializerCommandCreator(this.ioc);
        doThrow(RuntimeException.class).when(this.ioc).resolve(any());
        creator.createInitializer();
    }
}
