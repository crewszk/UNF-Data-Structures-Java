import java.io.*;
import java.math.*;
import java.util.Scanner;

public class Project1 {
	
	private static boolean nameSorted = false;

	public static void main(String[] args) throws FileNotFoundException{
		
	    Scanner input = new Scanner(System.in);
        System.out.println("COP3538 Project 1\nInstructor: Xudong Liu\n\nArray Searches and Sorts");
        String fileName = checkFile(input);
        short fileSize = scanFile(fileName);

        Country[] countryArray = new Country[fileSize - 1];
        
        loadArray(countryArray, countryArray.length, fileName);
        while(menuSelect(printMenu(input), countryArray, fileSize) == false)
        	{ }
        
        input.close();
    }
	
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

    public static short scanFile(String fileName) throws FileNotFoundException
    {
        File countryFile = new File(fileName);
        Scanner fileScan = new Scanner(countryFile);
        
        short i = 0;
        while(fileScan.hasNextLine())
        {
            i++;
            fileScan.nextLine();
        }

        System.out.printf("\n%d items were loaded\n", i);
        
        fileScan.close();
        return i;
    }
    
    public static void loadArray(Country[] countryArray, int arrSize, String fileName) throws FileNotFoundException
    {
    	File countryFile = new File(fileName);
    	Scanner scanFile = new Scanner(countryFile);
    	scanFile.useDelimiter("\\n");
    	String temp = scanFile.next();
    	scanFile.useDelimiter(",|\\n");
    	
    	for(int i = 0; i < arrSize; i++)
    	{
    		countryArray[i] = new Country();
    		countryArray[i].setName(scanFile.next());
    		countryArray[i].setCode(scanFile.next());
    		countryArray[i].setCapitol(scanFile.next());
    		countryArray[i].setPopulation(scanFile.nextInt());
    		countryArray[i].setGDP(new BigDecimal(scanFile.next()).longValue());
    		countryArray[i].setHappinessRank(scanFile.nextInt());
    	}
    }

    public static short printMenu(Scanner input)
    {
    	short output;
    	
    	System.out.print("\nChoose a menu item:\n1. Print a countries report\n2. Sort by Name\n3. Sort by Happiness" + 
    						"\n4. Sort by GDP per capita\n5. Find and print a given country\n6. Quit\nEnter your choice: ");
    	output = input.nextShort();
    	
    	return output;
    }
    
    public static boolean menuSelect(short selection, Country[] countryArray, short arrSize)
    {
    	Scanner input = new Scanner(System.in);
    	boolean exit = false;
    	switch(selection)
    	{
    	case 1: printReport(countryArray, arrSize);
    			break;
    	case 2: nameSort(countryArray, arrSize);
    			break;
    	case 3: happinessSort(countryArray, arrSize);
    			break;
    	case 4: gdpSort(countryArray, arrSize);
    			break;
    	case 5: System.out.print("Which country? Answer: ");
    			int result = searchCountry(countryArray, arrSize, input.next());
    			if (result == -1)
    				System.out.println("Country not found in file!\n");
    			else
    			{
    				System.out.println();
    				countryArray[result].printCountry();
    			}
    			break;
    	case 6: System.out.println("Exiting application...");
    			exit = true;
    			break;
    	default: System.out.println("INVALID CHOICE");
    			break;
    	}
    	
    	return exit;
    }
    
    public static void printReport(Country[] countryArray, short arrSize)
    {
    	System.out.printf("%-40s%-17s%-17s%-15s%-20s%-16s\n", "Country Name", "Country Code", "Capitol City", "Population", "GDP per Capita", "Happiness Rank");
    	for(int i = 0; i < 120; i++)
    		System.out.print("-");
    	System.out.println();
    	
    	for(int i = 0; i < arrSize - 1; i++)
    		countryArray[i].printCountryReport();
    	
    	for(int i = 0; i < 120; i++)
    		System.out.print("-");
    	
    	System.out.println("\n");
    	
    }
    
    public static void nameSort(Country[] countryArray, short arrSize)
    {
    	boolean sorted = true;
    	
    	for(int i = arrSize - 1; i > 0; i--)
    	{
    		sorted = true;
    		for(int j = 0; j < i - 1; j++)
    		{
    				if(countryArray[j].getName().compareToIgnoreCase(countryArray[j+1].getName()) > 0)
    				{
    					sorted = false;
    					sortArray(countryArray, j, j + 1);
    				}
    		}
    		
    		if(sorted)
    			break;
    	}
    	
    	nameSorted = true;
    }
    
    public static void happinessSort(Country[] countryArray, short arrSize)
    {
    	for(int i = 0; i < arrSize - 1; i++)
    	{
    		int lowestIndex = i;
    		for(int j = i + 1; j < arrSize - 1; j++)
    		{
    			if(countryArray[j].getHappinessRank() < countryArray[lowestIndex].getHappinessRank())
    				lowestIndex = j;
    		}
    		sortArray(countryArray, lowestIndex, i);
    	}
    	
    	nameSorted = false;
    }
    
    public static void gdpSort(Country[] countryArray, short arrSize)
    {
    	for(int i = 1; i < arrSize - 1; i++)
    	{
    		for(int j = i; j > 0; j--)
    		{
    			if(countryArray[j].getGDP() < countryArray[j - 1].getGDP())
    				sortArray(countryArray, j, j - 1);
    		}
    	}
    	
    	nameSorted = false;
    }
    
    public static int searchCountry(Country[] countryArray, short arrSize, String input)
    {
    	if(nameSorted)
    	{
    		int low = 0, high = arrSize - 1;
    		while (low <= high)
    		{
    			int k = (low + high) / 2;
    			int check = input.compareToIgnoreCase(countryArray[k].getName());
    			
    			if(check == 0)
    				return k;
    			else if(check > 0)
    				low = k + 1;
    			else if(check < 0)
    				high = k - 1;
    			else
    				break;
    			
    		}
    	}
    	else
    	{
    		for(int i = 0; i < arrSize - 1; i++)
    		{
    			if(input.compareToIgnoreCase(countryArray[i].getName()) == 0)
    				return i;    				
    		}
    	}
    	
    	return -1;
    }
    
    public static void sortArray(Country[] countryArray, int indexFirst, int indexSecond)
    {
    	Country temp = new Country();
    	
    	temp.setName(countryArray[indexFirst].getName());
		temp.setCode(countryArray[indexFirst].getCode());
		temp.setCapitol(countryArray[indexFirst].getCapitol());
		temp.setPopulation(countryArray[indexFirst].getPopulation());
		temp.setGDP(countryArray[indexFirst].getGDP());
		temp.setHappinessRank(countryArray[indexFirst].getHappinessRank());
		
		countryArray[indexFirst].setName(countryArray[indexSecond].getName());
		countryArray[indexFirst].setCode(countryArray[indexSecond].getCode());
		countryArray[indexFirst].setCapitol(countryArray[indexSecond].getCapitol());
		countryArray[indexFirst].setPopulation(countryArray[indexSecond].getPopulation());
		countryArray[indexFirst].setGDP(countryArray[indexSecond].getGDP());
		countryArray[indexFirst].setHappinessRank(countryArray[indexSecond].getHappinessRank());
		
		countryArray[indexSecond].setName(temp.getName());
		countryArray[indexSecond].setCode(temp.getCode());
		countryArray[indexSecond].setCapitol(temp.getCapitol());
		countryArray[indexSecond].setPopulation(temp.getPopulation());
		countryArray[indexSecond].setGDP(temp.getGDP());
		countryArray[indexSecond].setHappinessRank(temp.getHappinessRank());
    }
}