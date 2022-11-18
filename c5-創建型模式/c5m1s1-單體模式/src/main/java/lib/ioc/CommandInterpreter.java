package lib.ioc;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.function.Function;

import static lib.ioc.Command.NO_OP;
import static java.util.stream.Collectors.toList;
import static lib.utils.IteratorUtils.stream;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class CommandInterpreter {
    public Command interpret(JsonNode tree, Function<String, Command> commandFactory) {
        if (!tree.has("name")) {
            return NO_OP;
        }

        String commandName = tree.get("name").asText();
        Command command = commandFactory.apply(commandName);

        if (tree.has("children")) {
            command.setChildren(parseChildren(tree, commandFactory));
        }
        if (tree.has("args")) {
            command.setArguments(parseArguments(tree));
        }
        return command;
    }

    private List<Object> parseArguments(JsonNode tree) {
        return stream(tree.get("args").elements())
                .map(node -> node.isInt() ? node.intValue() : node.textValue())
                .collect(toList());
    }

    private List<Command> parseChildren(JsonNode tree, Function<String, Command> taskFactory) {
        return stream(tree.get("children").elements())
                .map(node -> interpret(node, taskFactory))
                .collect(toList());
    }
}
