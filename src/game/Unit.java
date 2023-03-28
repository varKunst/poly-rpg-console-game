package game;

import java.util.*;

public class Unit {

	private String name;
	private int hp;
	public final int MAX_HP;
	private int sp;
	public final int MAX_SP;
	private int attack;
	private int defense;
	private boolean dead;
	
	private ArrayList<Skill> skillList;
	
	public Unit(String name, int hp, int sp, int att, int def) {
		this.name = name;
		this.MAX_HP = hp;
		this.hp = this.MAX_HP;
		this.MAX_SP = sp;
		this.sp = this.MAX_SP;
		this.attack = att;
		this.defense = def;
		this.skillList = new ArrayList<>();
	}
	
	public void attack(Unit unit) {
		int ranNum = GameManager.ran.nextInt(this.attack);
		int damage = this.attack - unit.getDefense() + ranNum;
		int hit = 0;
		
		while(damage>0) {
			unit.setHp(unit.getHp()-1);
			damage--;
			hit++;
			
			if(unit.getHp()==0) {
				unit.setDead(true);
				break;
			}
		}
		
		System.out.printf("%s의 공격!\n%s에게 %d의 데미지를 입혔다.\n", this.name, unit.getName(), hit);
		if(unit.isDead()) {
			System.out.printf("%s is DEAD...\n%s WIN!\n", unit.getName(), this.name);
			return;
		}
	}
	
	public void useSkill(Unit unit, Unit enemy) {
		
	}
	
	public void useItem() {
		
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSp() {
		return this.sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getAttack() {
		return this.attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public boolean isDead() {
		return this.dead;
	}

	public ArrayList<Skill> getSkillList() {
		return this.skillList;
	}

	public void setSkillList(ArrayList<Skill> skillList) {
		this.skillList = skillList;
	}
}