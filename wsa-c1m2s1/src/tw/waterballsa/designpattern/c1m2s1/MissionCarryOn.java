package tw.waterballsa.designpattern.c1m2s1;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class MissionCarryOn {
    private State state = State.ONGOING;
    private final Student student;
    private final Mission mission;

    public String getMissionName() {
        return mission.getName();
    }

    public enum State {
        ONGOING, COMPLETED
    }

    public MissionCarryOn(Student student, Mission mission) {
        this.student = student;
        this.mission = mission;
    }

    public void complete() {
        this.state = State.COMPLETED;
        System.out.printf("【任務】學員 %s 已成功完成任務 “%s”。\n",
                student.getAccount(), mission.getName());
        student.gainExp(mission.calculateExpAward());
    }

    public State getState() {
        return state;
    }

    public Student getStudent() {
        return student;
    }

    public Mission getMission() {
        return mission;
    }
}
