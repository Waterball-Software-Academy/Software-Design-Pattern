package commons;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static commons.ValidationUtils.lengthShouldBeWithin;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Message {
    private final User author;
    private final User target;
    private final String content;
    private final LocalDateTime createdTime;

    public Message(User author, User target, String content) {
        this(author, target, content, now());
    }

    public Message(User author, User target, String content, LocalDateTime createdTime) {
        this.author = author;
        this.target = target;
        this.content = lengthShouldBeWithin(content, 1, 2000);
        this.createdTime = createdTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Message edit(String content) {
        return new Message(author, target, content, createdTime);
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public User getTarget() {
        return target;
    }

    public String getAuthorName() {
        return author.getNickname();
    }

    public String getTargetName() {
        return target.getNickname();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return author.equals(message.author) && target.equals(message.target) && content.equals(message.content) && createdTime.equals(message.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, target, content, createdTime);
    }
}
