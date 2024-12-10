package v1;

import commons.Email;
import commons.Message;
import commons.User;

public class Main {
    public static void main(String[] args) {
        User waterball = new User(new Email("johnny", "waterballsa.tw"), "水球");
        User tsen = new User(new Email("tsen", "waterballsa.tw"), "岑岑");
        Message message = new Message(waterball, tsen,
                "Could you tell the following people (wally@waterballsa.tw, fixiabis@waterballsa.tw, fong-putao@waterballsa.tw)\n\n" +
                        "that I'm gonna take a leave today? Thanks!"
        );

        System.out.println("====== EmailFormattingAndParagraphingMessenger =====");
        new EmailFormattingAndParagraphingMessenger().sendMessage(message);
        System.out.println("\n====== EmailHidingAndParagraphingMessenger =====");
        new EmailHidingAndParagraphingMessenger().sendMessage(message);
    }
}
