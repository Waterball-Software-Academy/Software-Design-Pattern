package lib.ioc;

import static java.util.Arrays.copyOfRange;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class CommandLine {
    private final String[] tokens;

    public CommandLine(String rawCommandLine) {
        this.tokens = rawCommandLine.split(" ");
    }

    public String[] getTokens() {
        return tokens;
    }
}
