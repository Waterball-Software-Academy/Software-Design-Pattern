package hero;

import org.jetbrains.annotations.Nullable;

/**
 * @author johnny@waterballsa.tw
 */
public class Pet {
    private final String name;

    @Nullable
    private Hero owner;

    public Pet(String name) {
        this.name = name;
    }

    public void eat(Fruit fruit) {
        System.out.println("寵物吃水果⋯⋯");

        if (owner != null) {
            owner.setHp(owner.getHp() + 10);
        }
    }

    public String getName() {
        return name;
    }

    public void setOwner(@Nullable Hero owner) {
        this.owner = owner;
    }

    public Hero getOwner() {
        return owner;
    }
}
