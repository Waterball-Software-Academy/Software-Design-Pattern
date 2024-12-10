package hero;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static utils.ValidationUtils.shouldBeWithinRange;

/**
 * @author johnny@waterballsa.tw
 */
public class Guild {
    private final String name;
    private final Set<Hero> members;

    public Guild(String name, List<Hero> members) {
        this.name = name;
        shouldBeWithinRange("Guild's members", members.size(), 1, 10);
        this.members = new HashSet<>(members);
    }

    public void join(Hero member) {
        if (members.size() == 10) {
            throw new IllegalStateException("Cannot join the guild since it's already had 10 members.");
        }
        if (members.contains(member)) {
            throw new IllegalStateException("Cannot join the guild twice.");
        }

        members.add(member);
    }

    public void leave(Hero member) {
        if (members.size() == 1) {
            throw new IllegalStateException("The only member of the guild cannot leave.");
        }
        if (!members.contains(member)) {
            throw new IllegalStateException("Only the member of the guild can leave.");
        }
        members.remove(member);
    }

    public String getName() {
        return name;
    }

    public Collection<Hero> getMembers() {
        return unmodifiableSet(members);
    }
}
