/**
 * COP 3990: Project 5 - Hash Tables
 * <p>
 * This Project5 class is the class that houses main for this program. Its main purpose
 * is to utilize Hash Tables to store Country name and GDP Per Capita information. The main method
 * runs through all of the HashTable class methods to show its function.
 * <p>
 * @author Zackery Crews - n00826481
 * @version 12-3-2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Project5 {

/**
 * This is the main method of the entire program that utilizes the Project5 and HashTable classes.
 * It will run through and ask the user for a file name to be used for the HashTable. It will
 * repeated until a real file is found. It will the scan the file and load the Hash Table with its
 * contents, and then display everything in the Hash Table. Next, it deletes 3 countries, searches
 * for 3 countries, and deletes 5 countries, then displays the Hash Table one last time. Lastly it
 * will search through the Hash Table and then display all the empty spaces and collisions in the
 * Hash Table.
 * 
 * @param args
 * @throws FileNotFoundException - Required for eclipse handling even though I manually check for
 * 									the file.
 */
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner input = new Scanner(System.in);
		System.out.println("COP3538 Project 5\nInstructor: Xudong Liu\n\nHash Tables");
	    String fileName = checkFile(input);
	    
	    HashTable table = new HashTable();
	    scanFileLoadTable(fileName, table);
	    table.display();
	    
	    System.out.println();
	    table.delete("Australia");
	    table.delete("Tunisia");
	    table.delete("Norway");
	    
	    System.out.println();
	    find("Sri Lanka", table);
	    find("North Cyprus", table);
	    find("Tunisia", table);
	    
	    System.out.println();
	    table.delete("Malawi");
	    table.delete("Germany");
	    table.delete("Ireland");
	    table.delete("Greece");
	    table.delete("Bolivia");
	    System.out.println();
	    table.display();
	    
	    table.printFreeAndCollisions();
	}

	/**
	 * This method will ask the user to continuously enter a filename until one is found
	 * 
	 * @param input - a Scanner object used for user input
	 * @return String - returns a string thats the filename of a file that exists
	 */
		public static String checkFile(Scanner input)
		{
			String filePath;
			File test;
			
			do {
				System.out.print("Enter a filename: ");
				filePath = input.next();
				test = new File(filePath);
				if(!test.exists())
				{
					System.out.println("File does not exist please try again!");
				}
				else
				{
					break;
				}
			} while(true);
			
			return filePath;
		}

	/**
	 * This method will take the filename specified by the user and scan it into a HashTable object. It will only
	 * scan the Name of the country in the file, and it will calculate its GDP per Capita based on its population and GDP
	 * in the files, both of which will be encapsulated in a Node object and inserted into the HashTable
	 * 
	 * @param fileName - The name of the file that exists
	 * @param table - The HashTable that will have information from the file inserted into
	 * @throws FileNotFoundException - required by the Eclipse IDE even though I manually check for it
	 */
		public static void scanFileLoadTable(String fileName, HashTable table) throws FileNotFoundException
		{
	        File countryFile = new File(fileName);
	        Scanner fileScan = new Scanner(countryFile);
	        fileScan.useDelimiter("\\n");
	    	fileScan.next();
	    	fileScan.useDelimiter(",|\\n");
	    	
	    	String name = "";
	    	long pop = 0;
	    	long gdpPC = 0;
	    	
	        while(fileScan.hasNextLine())
	        {
	        	name = fileScan.next();
	    		fileScan.next();
	    		fileScan.next();
	    		pop = Long.parseLong(fileScan.next());
	    		gdpPC = Double.valueOf(fileScan.next()).longValue() / pop;
	    		fileScan.next();  	
	            
	            table.insert(name, gdpPC);
	        }
	        
	        fileScan.close();
		}

/**
 * This method assists the HashTable find method by displaying if the searched country is found or
 * not.
 * @param input - The name of the country that is to be searched for
 * @param table - The HashTable that will be searched for the country name
 */
		public static void find(String input, HashTable table)
		{
			double output = table.find(input);
			if(output == -1)
				System.out.printf("%s was not found!\n", input);
			else
				System.out.printf("%s was found! Its GDP per Capita is $%,.2f\n", input, output);
		}
}