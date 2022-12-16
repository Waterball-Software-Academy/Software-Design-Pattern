package commons;

import java.util.Objects;

import static java.lang.String.format;

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

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return format("%s@%s", username, domain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return username.equals(email.username) && domain.equals(email.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, domain);
    }
}
