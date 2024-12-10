package lib.ioc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lib.commands.AndCommand;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.util.Arrays.stream;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class WaterballContainer {
    private final Set<Class<?>> registered = new LinkedHashSet<>();
    private final Map<Class<?>, Object> instances = new LinkedHashMap<>();
    private final Map<String, Class<? extends Command>> commands = new HashMap<>();
    private final CommandInterpreter interpreter = new CommandInterpreter();

    public WaterballContainer() {
        registerCommand("and", AndCommand.class);
    }

    public <T> void registerCommand(String commandName, Class<? extends Command> type) {
        if (commands.containsKey(commandName)) {
            throw new IllegalArgumentException("Duplicate command name");
        }
        register(type);
        commands.put(commandName, type);
    }

    public <T> void register(Class<T> type) {
        registered.add(type);
    }


    public void register(Object instance) {
        if (registered.contains(instance.getClass())) {
            throw new DuplicateInstanceTypeException(instance);
        }
        registered.add(instance.getClass());
        instances.put(instance.getClass(), instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> type) {
        if (!registered.contains(type)) {
            throw new IllegalArgumentException("Type " + type.getName() + " not found.");
        }
        return (T) instances.computeIfAbsent(type, this::instantiate);
    }

    @SuppressWarnings("unchecked")
    private <T> T instantiate(Class<T> type) {
        Constructor<?> constructor = type.getConstructors()[0];
        Object[] parameters =
                stream(constructor.getParameterTypes())
                        .map(paramType -> instances.computeIfAbsent(paramType, this::instantiate))
                        .toArray(Object[]::new);
        try {
            return (T) constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    public void runScript(String jsonScript) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode scriptTree = objectMapper.readTree(jsonScript);
            Command command = interpreter.interpret(scriptTree, this::instantiateCommandByName);
            command.execute();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private Command instantiateCommandByName(String name) {
        if (!commands.containsKey(name)) {
            throw new IllegalStateException("Can't find the command '" + name + "'.");
        }
        return instantiate(commands.get(name));
    }
}
