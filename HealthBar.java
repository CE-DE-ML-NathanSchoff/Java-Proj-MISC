package maze1;

public class HealthBar {
	
	
	private int health;
	
	
	

	public HealthBar(int startHealth) {
		this.health = startHealth;
		
		
	}
	
	public void damage(int potion) {
		health -= potion;
		if(health < 0) {
			health = 0;
		}
	}
	
	
	public int getHealth() {
		
		return health;
	}
	public boolean death() {
		return health <= 0;
		
	}
	

	

}
