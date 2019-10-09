/**
 * COP 3538: Project 2 - Stacks and Priority Queues
 * <p> 	This is the main Project2 class that runs the program. Operations are as follows:
 * 		In the first phase the program will ask the user for a file name to use as input
 * 		and will check to see if it exists. It will then scan the file for the number of
 * 		poor, fair, good, very good, and excellent countries based on their GDP per Capita
 * 		that will be used to initialize the Priority classes based on the labels mentioned
 * 		above. Lastly it will print the amount of countries loaded for each Priority object
 * 
 * <p> 	The second phase starts by scanning the file and checking the countries GDP per Capita
 * 		again but this time it is loading those countries into the Priority object based
 * 		on the GDP per capita. Then it will print each Priority queue.
 * 
 * <p>	The last phase starts by initializing a Stack object based on the total size of all
 * 		Priority queues. It will then remove the maximum GDP per capita country from each
 * 		queue in the same order as above: poor, fair, good, very good, and then excellent.
 * 		This is done until each queue is empty. Lastly it will print the entire stack showing
 * 		that the Priority queues removed each country in the proper priority. 
 * 
 * @author 		Zackery Crews - n00826481
 * @version		10-02-2019
 */



import java.util.Scanner;
import java.io.*;

public class Project2 {

	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner input = new Scanner(System.in);
        System.out.println("COP3538 Project 2\nInstructor: Xudong Liu\n\nStacks and Priority Queues");
        String fileName = checkFile(input);
        int[] sizes = scanFile(fileName);
        
        Priority poor = new Priority(sizes[0]);
        Priority fair = new Priority(sizes[1]);
        Priority good = new Priority(sizes[2]);
        Priority veryGood = new Priority(sizes[3]);
        Priority excellent = new Priority(sizes[4]);
        
        System.out.printf("%d poor countries loaded\n%d fair countries loaded\n%d good countries loaded\n%d very good countries loaded\n%d excellent countries loaded\n", sizes[0], sizes[1], sizes[2], sizes[3], sizes[4]);
        
        loadPriorityQueue(fileName, poor, fair, good, veryGood, excellent);
        printPriorityQueue(poor, fair, good, veryGood, excellent);
        
        int totalSize = sizes[0] + sizes[1] + sizes[2] + sizes[3] + sizes[4];
        Stack stack = new Stack(totalSize);
        
        pushToStack(stack, poor, fair, good, veryGood, excellent);
        
        input.close();
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
 * This method scans through the file identified above and will check each countries GDP per Capita and classify them
 * based on the labels provided above: poor, fair, good, very good, and excellent represented by an integer array's
 * index: 0, 1, 2, 3, and 4 respectively. It will then return the array for later use
 * 
 * @param 		ileName - This is the file name that has been identified
 * @return		sizes - This is an array of sizes for each of the classifications of countries
 * 				that will be used to initialize the Priority objects later on
 * @throws FileNotFoundException - This is for redundancy required by Eclipse even though I've manually checked in
 * 									the previous method
 */
	public static int[] scanFile(String fileName) throws FileNotFoundException
    {
		int[] sizes = {0, 0, 0, 0, 0};
        File countryFile = new File(fileName);
        Scanner fileScan = new Scanner(countryFile);
        fileScan.useDelimiter("\\n");
    	fileScan.next();
    	fileScan.useDelimiter(",|\\n");
    	
    	long scan = 0, pop = 0;
        while(fileScan.hasNextLine())
        {
            fileScan.next();
            fileScan.next();
            fileScan.next();
            pop = Long.parseLong(fileScan.next());
            scan = Long.parseLong(fileScan.next());
            scan = scan / pop;
            if(scan < 1000)
            	sizes[0]++;
            else if(scan >= 1000 && scan < 5000)
            	sizes[1]++;
            else if(scan >= 5000 && scan < 20000)
            	sizes[2]++;
            else if(scan >= 20000 && scan < 50000)
            	sizes[3]++;
            else
            	sizes[4]++;    
            fileScan.nextLine();
        }       
        
        for(int i = 0; i < sizes.length; i++)
        	sizes[i]++;
        
        fileScan.close();
        return sizes;
    }

/**
 * This method loads each priority queue defined in main by scanning back through the file just as before, but
 * now that we know the total amount of countries for each classification, we can now load the queues without overflowing
 * the queue size. The country will be loaded into a temporary Country object, the GDP per Capita will be calculated and then
 * the country will be inserted into the Priority queue based on its classification.
 * 
 * @param fileName - This is the file name that has been identified
 * @param poor - This is a priority queue for countries below 1000 GDP per Capita
 * @param fair - This is a priority queue for countries between 1000 (inclusive) and 5000 (exclusive) GDP per Capita
 * @param good - This is a priority queue for countries between 5000 (inclusive) and 20000 (exclusive) GDP per Capita
 * @param veryGood - This is a priority queue for countries between 20000 (inclusive) and 50000 (exclusive) GDP per Capita
 * @param excellent - This is a priority queue for countries at and above 50000 GDP per Capita
 * @throws FileNotFoundException - This is for redundancy required by Eclipse even though I've manually check in the previous methods
 */
	public static void loadPriorityQueue(String fileName, Priority poor, Priority fair, Priority good, Priority veryGood, Priority excellent) throws FileNotFoundException
	{
		Country temp = new Country();
		File countryFile = new File(fileName);
        Scanner fileScan = new Scanner(countryFile);
        fileScan.useDelimiter("\\n");
    	fileScan.next();
    	fileScan.useDelimiter(",|\\n");
    	
    	long gdpPC = 0;
    	
    	while(fileScan.hasNextLine())
    	{
    		temp.setName(fileScan.next());
    		temp.setCode(fileScan.next());
    		temp.setCapitol(fileScan.next());
    		temp.setPopulation(Long.parseLong(fileScan.next()));
    		temp.setGDP(Long.parseLong(fileScan.next()));
    		temp.setHappinessRank(Integer.parseInt(fileScan.next()));
    		gdpPC = temp.getGDP() / temp.getPopulation();
    		
            if(gdpPC < 1000)
            	poor.insert(temp);
            else if(gdpPC >= 1000 && gdpPC < 5000)
            	fair.insert(temp);
            else if(gdpPC >= 5000 && gdpPC < 20000)
            	good.insert(temp);
            else if(gdpPC >= 20000 && gdpPC < 50000)
            	veryGood.insert(temp);
            else
            	excellent.insert(temp);
            fileScan.nextLine();
    	}
    	
    	System.out.println();
    	
    	fileScan.close();
	}

/**
 * This method will print each priority queue using the printQueue() method in the Priority class.
 * 
 * @param poor - This is a priority queue for countries below 1000 GDP per Capita
 * @param fair - This is a priority queue for countries between 1000 (inclusive) and 5000 (exclusive) GDP per Capita
 * @param good - This is a priority queue for countries between 5000 (inclusive) and 20000 (exclusive) GDP per Capita
 * @param veryGood - This is a priority queue for countries between 20000 (inclusive) and 50000 (exclusive) GDP per Capita
 * @param excellent - This is a priority queue for countries at and above 50000 GDP per Capita
 */
	public static void printPriorityQueue(Priority poor, Priority fair, Priority good, Priority veryGood, Priority excellent)
	{
		System.out.println("Queue of Poor Countries");
        poor.printQueue();
        System.out.println("\nQueue of Fair Countries");
        fair.printQueue();
        System.out.println("\nQueue of Good Countries");
        good.printQueue();
        System.out.println("\nQueue of Very Good Countries");
        veryGood.printQueue();
        System.out.println("\nQueue of Excellent Countries");
        excellent.printQueue();
	}

/**
 * This method will remove the item with most priority from each priority queue one at a time and push that object into the stack object.
 * It will then print the entire stack using the printStack() method from the Stack class
 * 
 * @param stack - This is a stack with a total size of all the sizes of the priority queues combined, and will house every of
 * 					each of the priority queues elements after being removed
 * @param poor - This is a priority queue for countries below 1000 GDP per Capita
 * @param fair - This is a priority queue for countries between 1000 (inclusive) and 5000 (exclusive) GDP per Capita
 * @param good - This is a priority queue for countries between 5000 (inclusive) and 20000 (exclusive) GDP per Capita
 * @param veryGood - This is a priority queue for countries between 20000 (inclusive) and 50000 (exclusive) GDP per Capita
 * @param excellent - This is a priority queue for countries at and above 50000 GDP per Capita
 */
	public static void pushToStack(Stack stack, Priority poor, Priority fair, Priority good, Priority veryGood, Priority excellent)
	{
		System.out.println("\nStack Contents:");
        while(poor.isEmpty() != true)
			stack.push(poor.remove());
        while(fair.isEmpty() != true)
			stack.push(fair.remove());
        while(good.isEmpty() != true)
			stack.push(good.remove());
        while(veryGood.isEmpty() != true)
			stack.push(veryGood.remove());
        while(excellent.isEmpty() != true)
			stack.push(excellent.remove());
        
        stack.printStack();
	}
}


