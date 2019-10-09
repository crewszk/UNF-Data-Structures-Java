/**
 * This class is a Stack class. It creates a stack based on a size given, and will be able to push Country objects
 * onto the stack and pop them off the stack as well.
 * 
 * @author 		Zackery Crews - n00826481
 * @version		10-02-2019
 */

public class Stack {

	private Country[] arr;
	int size;
	
	Stack()
	{
		
	}
	
	Stack(int size)
	{
		this.size = 0;
		arr = new Country[size];
	}
	
	/**
	 * This method pushes a supplied object onto the top of the stack
	 * @param input - the country object being pushed onto the stack
	 */
	public void push(Country input)
	{
		if(isFull())
		{
			System.out.println("ERROR, THE STACK IS CURRENTLY FULL");
		}
		else
		{
			arr[this.size] = input;
			this.size++;		
		}
	}
	
	/**
	 * This method pops a country object off the top of the stack
	 * @return it will return the country object pushed off the top of the stack or return null if empty
	 */
	public Country pop()
	{
		if(isEmpty())
		{
			System.out.println("ERROR, THE STACK IS CURRENTLY EMPTY");
		}
		else
		{
			this.size--;
			return arr[this.size + 1];
		}
		
		return null;
	}
	
	/**
	 * This method will print every element in the stack in a proper table format
	 */
	public void printStack()
	{
		System.out.printf("%-40s%-17s%-17s%-15s%-20s%-16s%-14s\n", "Country Name", "Country Code", "Capitol City", "Population", "GDP", "Happiness Rank", "GDP Per Capita");		
		for(int i = 0; i < this.size - 1; i++)
		{
			arr[i].printCountryReport();
			System.out.printf("%-14.0f\n", (float)(arr[i].getGDP() / arr[i].getPopulation()));
		}
	}
	
	/**
	 * This method will check if the stack is empty
	 * @return a boolean true or false based on if the stack is empty or not
	 */
	public boolean isEmpty()
	{
		boolean output;
		
		if(this.size == 0)
			output = true;
		else
			output = false;
		
		return output;
	}
	
	/**
	 * This method will check if the stack is currently full
	 * @return a boolean true or false based on if the stack is full or not
	 */
	public boolean isFull()
	{
		boolean output;
		
		if(this.size == arr.length)
			output = true;
		else
			output = false;
		
		return output;
	}
	
	
}
