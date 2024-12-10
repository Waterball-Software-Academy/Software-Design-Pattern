package tw.waterballsa.degisnpattern.c2m3s1.v3;

import java.io.IOException;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {

    public static void main(String[] args) throws IOException {
        WaterballBot waterballBot = new WaterballBot(
                new HelpMessageHandler(new DcardHandler(new CurrencyHandler(null))));
        waterballBot.connect();
    }

}
