package tw.waterballsa.designpattern.c1m2s1;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Student {
    private final static LevelSheet LEVEL_SHEET = new LevelSheet();
    private final String account;
    private final String password;
    private int level = 1;
    private int exp = 0;
    private List<MissionCarryOn> missionCarryOns;
    private List<Adventurer> adventurers;

    public Student(String account, String password,
                   List<MissionCarryOn> missionCarryOns,
                   List<Adventurer> adventurers) {
        this.account = account;
        this.password = password;
        setAdventures(adventurers);
        setMissionCarryOns(missionCarryOns);
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    private void setAdventures(List<Adventurer> adventurers) {
        this.adventurers = requireNonNull(adventurers);
    }

    public MissionCarryOn carryOn(Mission mission) {
        System.out.printf("【任務】學員 %s 開始新任務：“%s”\n", account, mission.getName());
        MissionCarryOn missionCarryOn = new MissionCarryOn(this, mission);
        missionCarryOns.add(missionCarryOn); // 單向關聯
        return missionCarryOn;
    }

    public List<MissionCarryOn> getMissionCarryOns() {
        return missionCarryOns;
    }

    private void setMissionCarryOns(List<MissionCarryOn> missionCarryOns) {
        this.missionCarryOns = requireNonNull(missionCarryOns);
    }

    public void gainExp(int exp) {
        this.exp += exp;
        int newLevel = LEVEL_SHEET.query(this.exp);
        int levelUp = newLevel - level;
        System.out.printf("【獎勵】學員 %s 獲得經驗值 %d。\n", account, exp);
        for (int i = 0; i < levelUp; i++) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level ++;
        System.out.printf("【升等】學員 %s 等級提升至 %d。\n", account, level);
    }

    public String getAccount() {
        return account;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }
}
