package common;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Student {
    private final String name;
    private final int experience; // 年資
    private final String language; // 程式語言專長
    private final String jobTitle; // 職稱
    private final boolean[] availableTimeSlots; // 早上九點到晚上十點之間空閒的時間段

    public Student(String name, int experience, String language, String jobTitle, boolean[] availableTimeSlots) {
        this.name = name;
        this.experience = experience;
        this.language = language;
        this.jobTitle = jobTitle;
        this.availableTimeSlots = availableTimeSlots;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public String getLanguage() {
        return language;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean[] getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    @Override
    public String toString() {
        return format("%s %dy %s %s [%s]", name, experience, language, jobTitle, availableTimeSlotsToString());
    }

    private String availableTimeSlotsToString() {
        return range(0, availableTimeSlots.length)
                .filter(i -> availableTimeSlots[i])
                .mapToObj(i -> String.valueOf(i+9 /*start with 9 PM*/))
                .collect(joining(" "));
    }

}
