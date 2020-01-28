/**
 * This class is the Project4 class and houses the main function of the program. The purpose of the
 * project is to have a user scan a file of countries and load them into a Binary Search Tree while
 * having the ability to manipulate the BST in many ways. The main functions showcases all of these
 * functions available
 * 
 * @author Zackery Crews - n00826481
 * @version 11-17-2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project4 {

/**
 * This is the main function of the program. First it will ask the user to input a filename of their
 * choosing until an existing file is found, and each will invoke the checkFile method to see if it exists.
 * Next it will showcase all of the BinarySearchTree classes methods in various ways.
 * 	
 * @param args
 * @throws FileNotFoundException - Needed for eclipse IDE even though I check for it manually
 */
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner input = new Scanner(System.in);
        System.out.println("COP3538 Project 4\nInstructor: Xudong Liu\n\nBinary Search Trees");
        String fileName = checkFile(input);
        
        BinarySearchTree tree = new BinarySearchTree();
        scanFileLoadTree(fileName, tree);
        
        System.out.printf("\nInorder Traversal:\n\n%-25s%-20s\n", "Name", "GDP Per Capita");
        System.out.println("---------------------------------------------");
        tree.printInOrder(tree.getRoot());
        
        System.out.println();
        tree.delete("Australia");
        tree.delete("Tunisia");
        tree.delete("Norway");
        System.out.println();
        
        System.out.printf("\nPreorder Traversal:\n\n%-25s%-20s\n", "Name", "GDP Per Capita");
        System.out.println("---------------------------------------------");
        tree.printPreOrder(tree.getRoot());
        
        find("Sri Lanka", tree);
        find("North Cyprus", tree);
        find("Tunisia", tree);
        
        System.out.println();
        tree.delete("Malawi");
        tree.delete("Somalia");
        tree.delete("Ireland");
        tree.delete("Greece");
        tree.delete("Singapore");
        System.out.println();
        
        System.out.printf("\nPostorder Traversal:\n\n%-25s%-20s\n", "Name", "GDP Per Capita");
        System.out.println("---------------------------------------------");
        tree.printPostOrder(tree.getRoot());
        
        System.out.printf("\nBottom ten countries regarding GDP per Capita:\n\n%-25s%-20s\n", "Name", "GDP Per Capita");
        System.out.println("---------------------------------------------");
        tree.printBottomTen();
        System.out.printf("\nTop ten countries regarding GDP per Capita:\n\n%-25s%-20s\n", "Name", "GDP Per Capita");
        System.out.println("---------------------------------------------");
        tree.printTopTen();
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
 * This method will take the filename specified by the user and scan it into a BinarySearchTree object. It will only
 * scan the Name of the country in the file, and it will calculate its GDP per Capita based on its population and GDP
 * in the files, both of which will be encapsulated in a Node object and inserted into the BST
 * 
 * @param fileName - The name of the file that exists
 * @param tree - The BST that will have information from the file inserted into
 * @throws FileNotFoundException - required by the Eclipse IDE even though I manually check for it
 */
	public static void scanFileLoadTree(String fileName, BinarySearchTree tree) throws FileNotFoundException
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
            fileScan.nextLine();
            
            tree.insert(name, gdpPC);
        }
        
        fileScan.close();
	}
	
/**
 * This method assists the BinarySearchTree find method by displaying if the searched country is found or
 * not.
 * @param input - The name of the country that is to be searched for
 * @param tree - The BST that will be searched for the country name
 */
	public static void find(String input, BinarySearchTree tree)
	{
		int output = tree.find(input);
		if(output == -1)
			System.out.printf("%s was not found!\n", input);
		else
			System.out.printf("%s was found! Its GDP per Capita is $%d\n", input, output);
	}
}
