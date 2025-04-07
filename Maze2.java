package maze1;
import java.io.BufferedReader;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Maze2 {
	private int[][] adjMatrix;
	private List<String> vertices;
	private  Map<String,Integer> vertIndex;
	




	//public static void main(String[] args) throws IOException {
	public Maze2(File filePath) throws IOException {
		
	// Single File path
		//File filePath = new File("C:\\Users\\Natha\\OneDrive\\Desktop\\DataStruc\\map_0000.txt");
		
		//br intialization
		BufferedReader read = new BufferedReader(new FileReader(filePath));
		
		// map for matrix
		Map<String,Integer> m = new HashMap<String,Integer>();
		//stores the vertex
		List<String> vert = new ArrayList();
		//temp store edge
		List<String[]> edge = new ArrayList();
		// count obv
		int vCount = 0;
		
		// iterates through file and trims  
		String line;
		
		while((line = read.readLine()) != null) {
			
			if(line.startsWith("#") || line.trim().isEmpty())continue;
			//makes it readable, 
			String[] parts = line.split("\\s+");
			edge.add(parts);
			
		//main vertex
			if(!m.containsKey(parts[0])) {
				m.put(parts[0], vCount++);
				vert.add(parts[0]);
				
			}
			//the neighbor vertex
			for(int i = 1; i < parts.length;i+=2) {
				if(!m.containsKey(parts[i])) {
					m.put(parts[i], vCount++);
					vert.add(parts[i]);
					
					
				}
			}
			
			
		}
		
		read.close();
		// new intialization
		this.adjMatrix = new int[vCount][vCount];
		
		
		
		//intializes the adjancy matrix
		
	//	int[][] adjMatrix = new int[vCount][vCount];
		for(int i = 0; i<vCount;i++) {
			// actually does the work, -1 means no worky
			Arrays.fill(adjMatrix[i], -1); 
			
			
		}
		// fills the matrix, like opposite of NEO
		for(String[] ed: edge) {
			String vertex = ed[0];
			int vertIndex = m.get(vertex);
			
			
			for(int i = 1;i < ed.length;i+= 2) {
				String neighbor = ed[i];
				
				int weight = Integer.parseInt(ed[i + 1]);
				int neighborIn = m.get(neighbor);
				
				
				adjMatrix[vertIndex][neighborIn] = weight;
				adjMatrix[neighborIn][vertIndex] = weight;
			}
		}
		this.vertices = vert;
		this.vertIndex = m;
	//	System.out.print("test");
		
		//tester 
		for(String vertex: vert) {
			System.out.print(vertex + " ");
		}
		System.out.println();
		
		//prints graph
		for(int i = 0; i < vCount;i++) {
			System.out.print(vert.get(i) + " | ");
			
			for(int j = 0;j<vCount;j++) {
				System.out.print(adjMatrix[i][j] == -1 ? "|": adjMatrix[j][i] + "|");
				
			}
			System.out.println();
			
		}
		
		

	}
	public boolean depthSearch(String svertex, String treasure, HealthBar health, int damageAmount, int pIndex) {
		if(!vertIndex.containsKey(svertex)) {
			System.out.print("not found find start again");
			return false;
		}
		if(!vertIndex.containsKey(treasure)) {
			System.out.print("no gold here hero");
			return false;
			
		}
		boolean[] visited = new boolean[vertices.size()];
		
		
		Stack<Integer> st = new Stack<>();
		Map<Integer,Integer> pmap = new HashMap<>();
		
		int startInd = vertIndex.get(svertex);
		int treasureInd = vertIndex.get(treasure);
		
		
		st.push(startInd);
		pmap.put(startInd, -1);
		
		while(!st.isEmpty() && !health.death()) {
			int vertIndex = st.pop();
			health.damage(1);
			if(!visited[vertIndex]) {
				visited[vertIndex] = true;
				if(vertIndex == treasureInd) {
					printPath(pmap,treasureInd);
					return true;
					
				}
				System.out.print(vertices.get(vertIndex)+ " ");
				
				for(int i = 0; i < adjMatrix[vertIndex].length;i++) {
					if(adjMatrix[vertIndex][i] != -1 && !visited[i]) {
						st.push(i);
						pmap.put(i,vertIndex);
						
						if(i == pIndex) {
							health.damage(damageAmount);
							
						}
						
						//if(adjMatrix[vertIndex][i].equals("0")) {
							
						
						
						//health.potion(adjMatrix[vertIndex][i]);
						
						
					}
				}
			}
		}
		System.out.print("fail");
		return false;
		
		
		
	}
	
	public void printPath(Map<Integer,Integer> pmap, int treasureInd) {
		Stack<Integer> pathst = new Stack<>();
		
		int current = treasureInd;
		
		while(current != -1) {
			pathst.push(current);
			current = pmap.get(current);
			
		}
		System.out.print("Path: ");
		while(!pathst.isEmpty()) {
			System.out.print(vertices.get(pathst.pop()) + " ");
			
		}
		System.out.println();
		
	}
}

