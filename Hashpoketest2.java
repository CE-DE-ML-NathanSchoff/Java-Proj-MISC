package p001;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Hashpoketest2 implements pokedex {
	private HashNode[] table;         // Array for hash table
    private int size;                 // Number of elements
    private final double LOAD_FACTOR_THRESHOLD = 0.5; // when it hits peak, it re shuffles
    private final int INITIAL_CAPACITY = 800; // size of csv file
    String filePath = "C:\\Users\\Natha\\OneDrive\\Desktop\\pokemon_pokedex_alt.csv";

    public Hashpoketest2() {
        table = new HashNode[INITIAL_CAPACITY];
        size = 0;
    }

    // Actual hashing
    private int hashing(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Resizes and hashes them again
    private void resize() {
        HashNode[] oldTable = table;
        table = new HashNode[oldTable.length * 2];
        size = 0;

        // Reinsert old entries into the new table
        for (HashNode node : oldTable) {
            if (node != null) {
                add(node.key, node.value);
            }
        }
    }

    @Override
    public boolean add(String pokemon, ArrayList<String> entryList) {
        if ((double) size / table.length >= LOAD_FACTOR_THRESHOLD) {
            resize(); 
        }

        int index = hashing(pokemon);
        int i = 0;

        // Quadratic probe to find space
        while (table[index] != null) {
            if (table[index].key.equals(pokemon)) {
                table[index].value.addAll(entryList); // Add to existing list
                return true;
            }
            i++;
            index = (hashing(pokemon) + i * i) % table.length; // Quadratic probe
        }

        // Inserts new value
        table[index] = new HashNode(pokemon, entryList);
        size++;
        return true;
    }

    @Override
    public String find(String pokemon) {
        int index = hashing(pokemon);
        int i = 0;

        // Quadratic probe to find key
        while (table[index] != null) {
            if (table[index].key.equals(pokemon)) {
                return String.join(", ", table[index].value); // Return joined values
            }
            i++;
            index = (hashing(pokemon) + i * i) % table.length;
        }
        return null;
    }

    @Override
    public boolean delete(String pokemon) {
        int index = hashing(pokemon);
        int i = 0;

        // the quadtatic probe to delete
        while (table[index] != null) {
            if (table[index].key.equals(pokemon)) {
                table[index] = null;
                size--;
                return true;
            }
            i++;
            index = (hashing(pokemon) + i * i) % table.length;
        }
        return false;
    }
    public void populateFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;

            // Skips the index 0
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] rowValues = line.split(",");
                
                // null check
                if (rowValues.length > 5) {
                    String key = rowValues[5].trim(); // Use column F as the key
                    
                    ArrayList<String> valuesList = new ArrayList<>();
                    for (String value : rowValues) {
                        valuesList.add(value.trim()); // Adds the whole row
                    }
                    
                    // Insert into the hashmap
                    add(key, valuesList);
                } else {
                    System.out.println("Row is missing expected columns: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Natha\\OneDrive\\Desktop\\pokemon_pokedex_alt.csv";
        Hashpoketest2 pokedex = new Hashpoketest2();

        // Populate the hash table with values from the CSV
        pokedex.populateFromCSV(filePath);
        
        

        // Prompt the user to search for the pokemon
        Scanner scanner = new Scanner(System.in);
       
        System.out.print("Enter the name of the Pokémon to search: ");
        String pokemonName = scanner.nextLine().trim();
       

        // Search for the pokemon and print the result
        String entry = pokedex.find(pokemonName);
        if (entry != null) {
            System.out.println("Found entry for '" + pokemonName + "': " + entry);
        } else {
            System.out.println("No entry found for '" + pokemonName + "'.");
        }
        
        scanner.close();
    }

	public void printHT() {
		Hashpoketest2 pokedex2 = new Hashpoketest2();
		
		pokedex2.populateFromCSV(filePath);
		System.out.print(pokedex2);
		
		
		
		
		
	}

	@Override
	public double getLoadFactor() {
		
		return 0;
	}

	@Override
	public double getMaxLoadFactor() {
		return LOAD_FACTOR_THRESHOLD;
		
		
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void who() {
		// TODO Auto-generated method stub
		
	}

	public void help() {
		System.out.print("Hi, How can I help you today?");
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printHT(String[] arr) {
		// TODO Auto-generated method stub
		
	}




    }


