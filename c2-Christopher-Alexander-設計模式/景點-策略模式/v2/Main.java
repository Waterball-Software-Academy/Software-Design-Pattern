package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero("水之球", new Powerball());
        Hero hero2 = new Hero("大潘", new Earth());
        Game game = new Game(hero1, hero2);
        game.start();
    }
}
