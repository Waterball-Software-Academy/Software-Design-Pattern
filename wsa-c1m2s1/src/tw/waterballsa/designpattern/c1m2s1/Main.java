package tw.waterballsa.designpattern.c1m2s1;

import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        // 註冊學生
        Student student = new Student("johnny", "password", new ArrayList<>(), new ArrayList<>());

        // 開設旅程
        Journey designPattern = new Journey("軟體設計模式精通之旅", "我們只做最好的內容。", valueOf(6999),
                singletonList(new Chapter(1, "物件導向不新手的新手村",
                        singletonList(new Mission(1, "OOA｜建出你的領域模型吧！", new Challenge(1, "我做一次，你做一次！一起建出領域模型吧！"),
                                asList(new VideoScene("軟體設計模式精通之旅，一同展開冒險吧！", 1, 300),
                                        new VideoScene("這個世界正是物件導向的", 2, 500),
                                        new VideoScene("物件 vs. 類別", 3, 500),
                                        new VideoScene("類別的關係 (Relationship)", 4, 600)))))),
                new ArrayList<>(), new ArrayList<>());

        // 學員參與旅程
        Adventurer adventurer = designPattern.join(student);
        TourGroup tourGroup = adventurer.getTourGroup();
        List<Adventurer> adventurers = tourGroup.getAdventurers();

        // 查看學員目前正在執行的第一項任務
        MissionCarryOn missionCarryOn = student.getMissionCarryOns().get(0);
        System.out.printf("學員 %s 正在執行任務 “%s”。\n", student.getAccount(), missionCarryOn.getMissionName());

        // 完成這項任務
        missionCarryOn.complete();
    }
}
