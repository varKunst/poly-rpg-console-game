package game;

import java.nio.file.attribute.AclEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.util.*;

public class GamePlayer {
	
	private final int SIZE = 20;
	private ArrayList<ArrayList<Stage>> map = new ArrayList<>();
	private Player player;
	
	void run() {

		while(true) {			
		printGameMenu();
		
		char sel = GameManager.scan.next().charAt(0);
		if(sel=='1') 		playGame();
		else if(sel=='2')	printInventory();
		else if(sel=='3')	goToShop();
		else if(sel=='4')	getNewUnit();
		else if(sel=='0')	break;
		
		}
	}
	
	private void init() {
		for(int i=0; i<this.SIZE; i++) {
			ArrayList<Stage> line = new ArrayList<>();
			for(int j=0; j<this.SIZE; j++) {
				Stage block = new Stage();
				line.add(block);
			}
			this.map.add(line);
		}
		
		setPlayer();
		setEnemy();
		setWall();
	}
	
	private void setPlayer() {
		this.player = Player.getInstance();
		
		if(player.size()==0) {
			String name = GameManager.input("주인공의 이름을 정해주세요");
			Hero hero = new Hero(name, 100, 50, 100, 100);			
			this.map.get(player.getY()).get(player.getX()).setUnit(hero);
			this.map.get(player.getY()).get(player.getX()).setOccupied(true);
			
			this.player.add(hero);
		}		
	}
	
	private void setEnemy() {
		String[] enemies = {"Zombie", "Orc"};
		int count = GameManager.ran.nextInt(5) + 20;
		
		while(count>0) {
			int ranIdx = GameManager.ran.nextInt(enemies.length);
			String className = "game.Enemy" + enemies[ranIdx];
			
			try {
				Class<?> clazz = Class.forName(className);
				Object object = clazz.getDeclaredConstructor().newInstance();
				
				if(object instanceof Unit) {
					Unit unit = (Unit)object;
					
					int ranY = GameManager.ran.nextInt(this.SIZE-2) + 1;
					int ranX = GameManager.ran.nextInt(this.SIZE-2) + 1;
					
					Stage block = this.map.get(ranY).get(ranX);
					
					if(!block.isOccupied()) {
						block.setUnit(unit);
						block.setOccupied(true);
						
						count--;
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setWall() {
		int count = GameManager.ran.nextInt(10) + 40;
		
		while(count>0) {
			int ranY = GameManager.ran.nextInt(this.SIZE-2) + 1;
			int ranX = GameManager.ran.nextInt(this.SIZE-2) + 1;
			
			Stage block = this.map.get(ranY).get(ranX);
			
			if(!block.isOccupied()) {
				block.setWall(true);
				
				count--;
			}
		}
	}
	
	private void printGameMenu() {
		System.out.println("1. 플레이");
		System.out.println("2. 인벤토리");
		System.out.println("3. 상점");
		System.out.println("4. 동료찾기");
		System.out.println("0. 나가기");
	}

	private void printMap() {
		for(ArrayList<Stage> line: this.map) {
			for(Stage block: line) {
				System.out.print(block);
			}
			System.out.println();
		}
	}
	
	private void playGame() {
		init();
		while(true) {
			printMap();
			System.out.print("w(↑) a(←) s(↓) d(→): ");
			char dir = GameManager.scan.next().charAt(0);
			
			int yy = this.player.getY();
			int xx = this.player.getX();
			
			if(dir=='w')		yy--;
			else if(dir=='a')	xx--;
			else if(dir=='s')	yy++;
			else if(dir=='d')	xx++;
			
			if(yy<0 || yy>=this.SIZE || xx<0 || xx>=this.SIZE || this.map.get(yy).get(xx).isWall())
				continue;
			
			Unit unit = this.map.get(yy).get(xx).getUnit();
			
			if(unit!=null && unit instanceof Enemy) {
				boolean isGameOver = fight(player, unit);
				if(isGameOver) {
					System.out.println("Game Over...");
					return;
				}
			}
			
			Hero hero = this.player.get(0);
			
			this.map.get(this.player.getY()).get(this.player.getX()).setUnit(null);
			this.map.get(this.player.getY()).get(this.player.getX()).setOccupied(false);
			
			this.player.setY(yy);
			this.player.setX(xx);

			this.map.get(this.player.getY()).get(this.player.getX()).setUnit(hero);
			this.map.get(this.player.getY()).get(this.player.getX()).setOccupied(true);
		}
	}
	
	private boolean fight(Player player, Unit enemy) {
		System.out.printf("적 %s가 나타났다!\n", enemy.getName());
		while(true) {
			printFightCommand();
			
			int count = player.size();
			
			for(int i=0; i<count; i++) {
				Hero hero = player.get(i);
				System.out.printf("%s의 턴!\n", hero.getName());
				char comm = GameManager.scan.next().charAt(0);
				
				if(comm=='1')		hero.attack(enemy);
				else if(comm=='2')	hero.useSkill(hero, enemy);
				else if(comm=='3')	hero.useItem();
				else if(comm=='4') {
					System.out.println("무사히 도망쳤다!");
					break;
				}
				
				if(enemy.isDead()) {
					System.out.println("전투 종료!");
					return false;
				}
			}

			int ranIdx = GameManager.ran.nextInt(player.size());
			
			Hero hero = null;
			while(true) {
				hero = player.get(ranIdx);
				if(!hero.isDead())
					break;
			}
			
			enemy.attack(hero);
			
			boolean isDefeat = true;
			for(int i=0; i<player.size(); i++) {
				if(!player.get(i).isDead())
					isDefeat = false;
			}
			
			if(isDefeat) 
				return true;
		}
	}
	
	private void printFightCommand() {
		System.out.println("[1.공격한다] [2.스킬사용] [3.아이템] [4.도망친다]");
	}
	
	private void printInventory() {
		
	}
	
	private void goToShop() {
		
	}
	
	private void getNewUnit() {
		
	}
}