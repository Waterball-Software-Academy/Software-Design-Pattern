package commons;

import static java.lang.String.format;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class User {
    private final Email email;
    private final String nickname;

    public User(Email email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return format("%s (%s)", nickname, email);
    }
}
