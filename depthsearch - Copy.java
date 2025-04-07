package maze1;
import java.io.File;
import java.io.IOException;
//Driver Class


public class depthsearch {

	
//main
	public static void main(String[] args) throws IOException {
		
// file path, works with any
		File filePath = new File("C:\\Users\\Natha\\OneDrive\\Desktop\\DataStruc\\\\map_0011.txt");
	//Configuration class
		Maze2 graph = new Maze2(filePath);
		//Start and special area classification
		String startVertex = "A";
		String treasure  = "X";
		//Damage potion index
		int pIndex = 3;
		int damageAmount = 5;
		//Start health 
		int startHealth = 10;
		HealthBar health = new HealthBar(startHealth);
		
		
		//System.out.print("test");
		//compilation method
		
		//also includes a no treasure clause
		graph.depthSearch(startVertex,treasure,health, pIndex,damageAmount);
		
		//health after the battlefield 
		System.out.print("health remained: " + health.getHealth());
		

	}
}
