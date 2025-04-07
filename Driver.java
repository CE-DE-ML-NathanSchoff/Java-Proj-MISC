package maze1;
// use an adjency matrix
//define heart and items
//read and input file
//create a character and stats for zelda 
public class Driver {
	
	private boolean adjMatrix[][];
	private int numvert;
	
	
	public Driver(int numvert) {
		this.numvert = numvert;
		adjMatrix = new boolean[numvert][numvert];
		
		
	}
	
	public void addEdges(int i, int j) {
		adjMatrix[i][j] = true;
		adjMatrix[j][i] = true;
	}
	
	public void removeEdges(int i, int j) {
		adjMatrix[i][j] = false;
		adjMatrix[j][i] = false;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < numvert;i++) {
			s.append(i + ": ");
			for(boolean j: adjMatrix[i]) {
				s.append((j ? 1 : 0) + " ");
				
			}
			s.append("\n");
			
			
			
		}
		return s.toString();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
