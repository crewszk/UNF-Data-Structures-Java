/**
 * This class is the HashTable class and it is used to create HashTable objects. The 
 * HashTable itself is a Double Ended Linked-List using separate chaining as the collision management. 
 * The HashTable object holds Node object array named first, which notes the first Node in
 * the HashTable, and a Node object array named last, which notes the last Node in the
 * HashTable. It can insert, delete, and find a node, as well as display the entire
 * HashTable and display its empty spaces and the amount of collisions.
 * @author Zackery Crews - n00826481
 * @version 12-4-2019
 */

public class HashTable {
	
	/**
	 * This class is a Node object for the HashTable and will house a name, gdp per capita, a pointer
	 * to its next Node. It can print the node as well
	 * 
	 * @author Zackery Crews - n00826481
	 * @version 11-17-2019
	 */
	private class Node {
		String name;
		double gdpPerCapita;
		Node nextNode;
		
		public Node(String name, double gdpPerCapita) {
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;
		}
		
		public void printNode() {
			System.out.printf("%-25s%,-20.2f\n",  this.name, this.gdpPerCapita);
		}
	}
	
	private Node[] first;
	private Node[] last;
	
	/**
	 * This is the no-arg constructor for the HashTable class. It initializes both first
	 * and last as an array of Node objects of size 311
	 */
	HashTable(){
		this.first = new Node[311];
		this.last = new Node[311];
	}
	
	/**
	 * This method assists in the insert, find, and delete methods. It hash's the
	 * string, which is the Countries name, by adding each characters unicode value
	 * and modding sum by 311. This value will be the index of the specified Country.
	 * @param country - the string value of the country specified
	 * @return int - returns an int value that is used as the index of the specified country
	 * 				in the array
	 */
	public static int hashFunction(String country)
	{
		int code = 0;
		
		for(int i = 0; i < country.length(); i++)
			code += (int)country.charAt(i);
		
		code = code % 311;
		
		return code;
	}
	
	/**
	 * This is the method that inserts a specified Node object carrying the countries name
	 * and GDP Per Capita into the Hash Table. It hashes the countries name first to find
	 * the index of the array to be used. Next it will check if that index is null or already
	 * contains a Node in the first array, if it is null it will simply make that index in first
	 * contain the new Node but if it isn't null and contains another Node, it will update the Node
	 * following the last node as the new Node, then update the last node as the new Node.
	 * @param country - the name of the country to be inserted
	 * @param gdpPerCapita - the GDP per capita of the country to be inserted
	 */
	public void insert(String country, double gdpPerCapita) {
		
		int index = hashFunction(country);
		
		Node input = new Node(country, gdpPerCapita);
		
		if(this.first[index] == null) {
			this.first[index] = input;
			this.last[index] = input;
		}
		else {
			this.last[index].nextNode = input;
			this.last[index] = input;
		}
	}
	
	/**
	 * This method will search through the HashTable for the specified country name and 
	 * return its gdp per capita if found and a -1 if not found.
	 * @param country - the name of the specified country to be searched for
	 * @return double - a double value reflecting the gdp per capita of the country found or a
	 * 					-1 if the country is not found.
	 */
	public double find(String country) {
		
		double output;
		int index = hashFunction(country);
		
		Node current = this.first[index];
		
		while(true) {
			if(current == null) {
				output = -1;
				break;
			}
			else if(current.name.compareToIgnoreCase(country) == 0) {
				output = current.gdpPerCapita;
				break;
			}
			else {
				current = current.nextNode;
			}
		}
		
		return output;
	}
	
	/**
	 * This method deletes a specified country from the Hash Table. It will hash the
	 * countries name to find its index, then check if its null to return that it doesn't exist
	 * or if the first Node in the index contains the country specified to delete it and return that
	 * its been deleted. If the first Node isn't null, but also doesn't contain the country that
	 * needs to be deleted, it will loop until the country is found and deleted, or a null node is
	 * found denoting that the country doesn't exist in the Hash table.
	 * @param country - the name of the specified country to be deleted
	 */
	public void delete(String country) {
		
		int index = hashFunction(country);
		
		if(this.first[index] == null) {
			System.out.printf("%s doesn't exist!\n", country);
			return;
		}
		if(this.first[index].name.compareToIgnoreCase(country) == 0) {
			System.out.printf("%s was found and is deleted from the table!\n", country);
			this.first[index] = this.first[index].nextNode;
			return;
		}
		
		Node current = this.first[index];
		
		while(true) {
			if(current.nextNode == null) {
				System.out.printf("%s doesn't exist!\n", country);
				return;
			}
			else if(current.nextNode.name.compareToIgnoreCase(country) == 0) {
				System.out.printf("%s was found and is deleted from the table!\n", country);
				current.nextNode = current.nextNode.nextNode;
				return;
			}
			else {
				current = current.nextNode;
			}
		}
	}
	
	/**
	 * This method will loop through the entire Hash Table and display each index of the
	 * array as well as each node in the linked list of that specific array index.
	 */
	public void display() {
		
		Node current;
		
		for(int i = 0; i < first.length; i++) {
			System.out.printf("%d.", i);
			current = first[i];		
			if(current == null) {
				System.out.println("\tEmpty");
			}
			else {
				while(current != null) {
					System.out.print("\t");
					current.printNode();
					current = current.nextNode;
				}
			}
		}
	}
	
	/**
	 * This method will search through the Hash Table and print out the amount of 
	 * empty spaces as well as the amount of collisions that have happened in the Hash Table. 
	 */
	public void printFreeAndCollisions() {
		
		int empty = 0, collisions = 0;
		
		Node current;
		for(int i = 0; i < this.first.length; i++) {
			current = first[i];
			
			if(current == null) {
				empty++;
			}
			else {
				while(current.nextNode != null) {
					collisions++;
					current = current.nextNode;
				}
			}
		}		
		
		System.out.printf("\nThere are %d spaces available and %d total collisions in the hash table\n", empty, collisions);
	}
}
