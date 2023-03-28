package game;

public class Stage {
	
	private boolean occupied;
	private boolean wall;
	private Unit unit;
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public boolean isWall() {
		return wall;
	}
	
	public void setWall(boolean wall) {
		this.wall = wall;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString() {
		
		String output = "";

		if(this.occupied) {
			if(this.unit instanceof Hero) {
				output = "★ ";
			} else if(this.unit instanceof EnemyZombie) {
				output = "▲ ";
			} else if(this.unit instanceof EnemyOrc) {
				output = "◆ ";
			}
		} else {
			if(this.isWall()) {
				output = "□ ";
			} else {
				output = "· ";				
			}
		}
		
		return output;
	}
}
