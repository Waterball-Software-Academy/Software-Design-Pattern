package hero;

import org.jetbrains.annotations.Nullable;

import static utils.ValidationUtils.shouldBeGreaterThanOrEqual;

/**
 * @author johnny@waterballsa.tw
 */
public class Hero {
    private int level = 1;
    private int totalExp = 0;
    private int hp = 100;

    @Nullable
    private Pet pet;

    public Hero() {

    }

    public Hero(int level, int totalExp, int hp, LevelSheet levelSheet) {
        setTotalExp(totalExp);
        setLevel(levelSheet.queryLevel(totalExp));
        setHp(hp);
    }

    public void setPet(Pet pet) {
        if (this.pet != null) {
            this.pet.setOwner(null); // 棄養現有寵物
        }
        this.pet = pet;
        pet.setOwner(this); // 新寵物
    }

    public void removePet() {
        if (this.pet != null) {
            this.pet.setOwner(null); // 棄養現有寵物
        }
        this.pet = null;
    }



    @Nullable
    public Pet getPet() {
        return pet;
    }

    void setHp(int hp) {
        this.hp = shouldBeGreaterThanOrEqual("Hp", hp, 0);
        System.out.printf("英雄血量更新至 %d\n", hp);
    }

    private void setLevel(int level) {
        this.level = shouldBeGreaterThanOrEqual("Level", level, 1);
    }

    private void setTotalExp(int totalExp) {
        this.totalExp = shouldBeGreaterThanOrEqual("TotalExp", totalExp, 0);
    }

    public void gainExp(int exp, LevelSheet levelSheet) {
        shouldBeGreaterThanOrEqual("Gained Exp", exp, 0);
        int currentLevel = level;
        setTotalExp(totalExp + exp);
        setLevel(levelSheet.queryLevel(totalExp));
        System.out.printf("英雄目前等級 %d，獲得 %d EXP，最新總共經驗值為 %d，最新等級為 %d。\n", currentLevel, exp, totalExp, level);
    }

    public int getLevel() {
        return level;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public int getHp() {
        return hp;
    }

}
