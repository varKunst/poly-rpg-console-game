package game;

import java.util.*;

public class Player {
	
	private static ArrayList<Hero> unitList;
	private static int money;
	private int size;
	private static Player instance = new Player();
	
	private Inventory inven;
	
	private int y;
	private int x;
	
	private Player() {
		unitList = new ArrayList<>();
		money = 10000;
		this.size = 0;
		this.inven = new Inventory();
		this.y = 0;
		this.x = 0;
	}
	
	public void add(Hero unit) {
		Hero hero = new Hero(unit.getName(), unit.getHp(), unit.getSp(), unit.getAttack(), unit.getDefense());
		unitList.add(hero);
		this.size++;
	}
	
	public Hero get(int index) {
		return unitList.get(index);
	}
	
	public void remove(Hero unit) {
		unitList.remove(unit);
	}
	
	public int size() {
		return this.size;
	}

	public static ArrayList<Hero> getUnitList() {
		return (ArrayList<Hero>) unitList.clone();
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		Player.money = money;
	}

	public static Player getInstance() {
		return instance;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
