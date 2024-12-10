package v1b;

import commons.AirConditioner;
import commons.Fan;
import commons.Television;

import java.util.Scanner;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner();
        Fan fan = new Fan();
        Television tv = new Television();
        Controller controller = new Controller(ac, fan, tv);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Click a button (0~5): ");
            int button = in.nextInt();
            controller.press(button);
        }
    }
}
