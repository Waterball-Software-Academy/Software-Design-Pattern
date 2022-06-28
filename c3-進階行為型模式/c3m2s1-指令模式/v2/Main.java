package v2;

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

        Controller controller = new Controller();
        // dependency injection
        controller.setCommand(0, new FanNextLevelCommand(fan));
        controller.setCommand(1, new FanPreviousLevelCommand(fan));
        controller.setCommand(2, new TurnOnAirConditionerCommand(ac));
        controller.setCommand(3, new TurnOffAirConditionerCommand(ac));
        controller.setCommand(4, new TurnOnTvCommand(tv));
        controller.setCommand(5, new TurnOffTvCommand(tv));

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Click a button (0~5): ");
            int button = in.nextInt();
            controller.press(button);
        }
    }
}
