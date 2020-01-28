/**
 * COP 3538: Project 3 - Linked Lists
 * 
 * This is the main Project 3 class that does a number of things
 * Firstly, it will read Country data from a file after ensuring the file exists
 * Then, it will load that data into a stack and print the stack to the console.
 * Next, it will pop the data off the stack onto a queue, loading into the front of
 * the queue first and then the end of the queue next, repeated until all the 
 * data is loaded, which it will print the queue afterwards.
 * The program will look for any Country object that has a GDP per Capita between
 * 30000 - 40000 and remove it from the queue, and the print the queue a second time
 * Lastly, it will remove the Country data from the queue, removing from the front 
 * first, and then the end, loading the data into a stack. The program will then
 * print the stack
 * 
 * @author Zackery Crews - n00826481
 * @version 10-25-2019
 */

import java.util.Scanner;
import java.io.*;

public class Project3 {

	public static void main(String[] args) throws FileNotFoundException{

		Scanner input = new Scanner(System.in);
        System.out.println("COP3538 Project 3\nInstructor: Xudong Liu\n\nLinked Lists");
        String fileName = checkFile(input);
        
        Stack stack = new Stack();        
		scanFileLoadStack(fileName, stack);
		System.out.println("\nContents of the Stack:");
		stack.printStack();
		
		Queue queue = new Queue();
		loadQueue(stack, queue);
		System.out.println("\nContents of the Queue:");
		queue.printQueue();		
		
		deleteGDP(queue);
		System.out.println("\nContents of the Queue:");
		queue.printQueue();
		
		loadStack(queue, stack);
		System.out.println("\nContents of the Stack:");
		stack.printStack();
	}
	
	/**
	 *	This method asks the user for a filename, and it will check to see if the file name exists.
	 *	If the file does not exist, then it will notify the user and prompt them again. Once a proper
	 *	file name has be entered, it will return the file name back to main
	 *	
	 *	@param		input - I normally only initialize one Scanner object that is needed for user input
	 *				back in the main method and pass it onto any methods that require it to ensure there is no redundancy
	 *	@return		filePath - This is the file name that has been identified 
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
		 * This method will scan through the file that was found from the checkFile method and
		 * create Country objects if their GDP per Capita was in the Fair, Good, or Very Good thresholds
		 * of quality, and discard any others. The Country objects are pushed each time onto a stack
		 * @param fileName - The file name specified by the user and found on the system
		 * @param stack - the Stack object that will have Country objects pushed into it
		 * @throws FileNotFoundException - Required by eclipse even though I manually checked if the file
		 * 									existed or not
		 */
		public static void scanFileLoadStack(String fileName, Stack stack) throws FileNotFoundException
		{
	        File countryFile = new File(fileName);
	        Scanner fileScan = new Scanner(countryFile);
	        fileScan.useDelimiter("\\n");
	    	fileScan.next();
	    	fileScan.useDelimiter(",|\\n");
	    	
	    	Country temp = new Country();
	    	long gdpPC = 0;
	    	
	        while(fileScan.hasNextLine())
	        {
	        	temp.setName(fileScan.next());
	    		temp.setCode(fileScan.next());
	    		temp.setCapitol(fileScan.next());
	    		temp.setPopulation(Long.parseLong(fileScan.next()));
	    		temp.setGDP(Double.valueOf(fileScan.next()).longValue());
	    		temp.setHappinessRank(Integer.parseInt(fileScan.next()));
	            gdpPC = temp.getGDP() / temp.getPopulation();
	            if(gdpPC >= 1000 && gdpPC < 50000)
	            	stack.push(temp);  	
	            fileScan.nextLine();
	        }
	        fileScan.close();
		}

		/**
		 * This method will load a Queue object with data from a Stack. It will insert the data
		 * into the front of the queue first, and then the back next, repeating until the stack
		 * is empty
		 * @param stack - the Stack object that data is being popped from
		 * @param queue - the Queue object that data is being inserted to
		 */
		public static void loadQueue(Stack stack, Queue queue)
		{
			while(true) {
				if(stack.isEmpty() == false)
					queue.insertFront(stack.pop());
				else
					break;
				if(stack.isEmpty() == false)
					queue.insertEnd(stack.pop());
				else
					break;
			}
		}

		/**
		 * This method will take a Queue object of data, and search for a specific range of
		 * GDP per Capita Country objects, and if found within that range it will call the 
		 * Queue's findDelete method and remove that Country from the queue.
		 * @param queue - the Queue object that data is being scanned and removed from
		 */
		public static void deleteGDP(Queue queue)
		{
			float gdpPC;
			Link current = queue.first;
			while(current != null)
			{
				gdpPC = (float)(current.get().getGDP() / current.get().getPopulation());
				if(gdpPC > 30000.0 && gdpPC < 40000.0)
					queue.findDelete(current.get().getName());
				current = current.next;
			}
		}

		/**
		 * This method loads a Stack with data from a Queue, removing the data from the queue
		 * from the front first, and then the end next, repeating until the queue is empty
		 * @param queue - the Queue object that data is being removed from
		 * @param stack - the Stack object that data is being pushed to
		 */
		public static void loadStack(Queue queue, Stack stack)
		{
			while(true)
			{
				if(queue.isEmpty() == false)
					stack.push(queue.removeFront());
				else
					break;
				if(queue.isEmpty() == false)
					stack.push(queue.removeEnd());
				else
					break;
			}
		}
}
