package commons;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Email {
    private final String username;
    private final String domain;

    public Email(String username, String domain) {
        this.username = username;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return String.format("%s@%s", username, domain);
    }

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }
}
