package v2;

import static commons.Utils.printf;
import static java.lang.Math.max;

public class Hero {
	private int hp = 500;
	private final String name;
	private final AttackType attackType;

	public Hero(String name, AttackType attackType) {
		this.name = name;
		this.attackType = attackType;
	}

	public void attack(Hero hero) {
		attackType.attack(this, hero);
	}

	public void damage(int damage) {
		setHp(getHp() - damage);
		printf("英雄 %s 受到了 %d 點傷害，生命值剩下 %d。\n", name, damage, hp);
	}

	public boolean isDead() {
		return hp <= 0;
	}

	public String getName() {
		return name;
	}

	public void setHp(int hp) {
		this.hp = max(0, hp);
	}

	public int getHp() {
		return hp;
	}
}
