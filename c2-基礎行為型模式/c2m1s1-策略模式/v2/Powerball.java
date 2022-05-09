package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Powerball implements AttackType {
    @Override
    public void attack(Hero attacker, Hero attacked) {
        attacked.damage(500);
    }
}
