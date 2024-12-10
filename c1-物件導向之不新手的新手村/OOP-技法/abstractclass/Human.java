package abstractclass;

import java.util.Scanner;

/**
 * @author johnny@waterballsa.tw
 */
public class Human extends Player {
    private final static Scanner in = new Scanner(System.in);

    public Human(int number) {
        super(number);
    }

    @Override
    public Decision decide() {
        System.out.println("請出拳 (1) 剪刀 (2) 拳頭 (3) 布：");
        int num = in.nextInt();
        switch (num) {
            case 1:
                return Decision.SCISSORS;
            case 2:
                return Decision.STONE;
            case 3:
                return Decision.PAPER;
            default:
                System.out.println("只能輸入範圍 1~3 的數字，請再輸入一次。");
                return decide();
        }
    }
}
