package tw.waterballsa.designpattern.c1m2s1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static java.util.Objects.requireNonNull;
import static tw.waterballsa.designpattern.c1m2s1.ValidationUtils.lengthShouldBe;
import static tw.waterballsa.designpattern.c1m2s1.ValidationUtils.shouldBeBiggerThan;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Journey {
    private String name;
    private String description;
    private BigDecimal price;
    private List<Chapter> chapters;
    private List<Adventurer> adventurers;
    private List<TourGroup> tourGroups;

    public Journey(String name, String description, BigDecimal price,
                   List<Chapter> chapters,
                   List<Adventurer> adventurers, List<TourGroup> tourGroups) {
        setTourGroups(tourGroups);
        setAdventures(adventurers);
        setChapters(chapters);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public List<TourGroup> getTourGroups() {
        return tourGroups;
    }

    private void setTourGroups(List<TourGroup> tourGroups) {
        this.tourGroups = requireNonNull(tourGroups);
    }

    public Adventurer join(Student student) {
        int number = adventurers.size() + 1;

        // 建立與冒險者的雙向關聯
        Adventurer adventurer = new Adventurer(number, student, this);
        adventurers.add(adventurer);
        student.getAdventurers().add(adventurer);

        // 開始第一項任務
        Mission firstMission = getFirstMission();
        adventurer.carryOn(firstMission);

        // 匹配旅團
        TourGroup tourGroup = matchTourGroup(adventurer);
        tourGroup.add(adventurer);
        System.out.printf("【旅程】冒險者 %s 加入旅程 %s --> 匹配至旅團 %d。\n",
                student.getAccount(), getName(), tourGroup.getNumber());
        return adventurer;
    }

    private TourGroup matchTourGroup(Adventurer adventurer) {
        // 匹配算法：將新冒險者匹配至某個旅團中
        if (!tourGroups.isEmpty()) {
            // 初版：先隨便用隨機實作
            return tourGroups.get((int) (Math.random() * tourGroups.size()));
        }
        return new TourGroup(1, new ArrayList<>(List.of(adventurer)));
    }


    private Mission getFirstMission() {
        return getChapters().get(0).getFirstMission();
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    private void setAdventures(List<Adventurer> adventurers) {
        this.adventurers = requireNonNull(adventurers);
    }
    private void setChapters(List<Chapter> chapters) {
        this.chapters = requireNonNull(chapters);
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setName(String name) {
        this.name = lengthShouldBe(name, 1, 30); // char[1..30]
    }

    public void setDescription(String description) {
        this.description = lengthShouldBe(description, 0, 300); // char[1..300]
    }

    public void setPrice(BigDecimal price) {
        this.price = shouldBeBiggerThan(price, 1); // {>=1}
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
