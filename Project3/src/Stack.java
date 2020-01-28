/**
 * This class is a Stack class that utilizes a Singly Linked List of Country objects
 * as a data structure. It can create a Stack of any size needed, and
 * can push, pop, and print data. 
 * 
 * @author Zackery Crews - n00826481
 * @version 10-19-2019
 */

public class Stack {

	Link first;
	
	Stack(){ }
	
	
	/**
	 * This method pushes data of type Country onto the top of the stack
	 * @param data - A Country object
	 */
	public void push(Country data)
	{
		if(isFull())
			System.out.println("ERROR, THE STACK IS FULL");
		else {		
			Link l = new Link(new Country(data.getName(), data.getCode(), data.getCapitol(), data.getPopulation(), data.getGDP(), data.getHappinessRank()));
			if(first != null)
				l.next = first;
			first = l;			
		}
	}
	
	/**
	 * This method pops data of type Country off the top of the stack
	 * @return output - a Country object is returned to the caller
	 */
	public Country pop()
	{
		if(isEmpty())
		{
			System.out.println("ERROR, STACK IS EMPTY");
			return null;
		}
		else
		{
			Country output = first.get();
			first = first.next;
			return output;
		}
	}

	/**
	 * This method walks through the stack and prints every element starting from
	 * the front, and printing the data in a formated table until null is reached 
	 */
	public void printStack()
	{
		System.out.printf("%-40s%-17s%-17s%-15s%-20s%-16s%-14s\n", "Country Name", "Country Code", "Capitol City", "Population", "GDP", "Happiness Rank", "GDP Per Capita");
		Link current = first;
		while(current != null) {
			current.get().printCountryReport();
			System.out.printf("%-14.0f\n", (float)(current.get().getGDP() / current.get().getPopulation()));
			current = current.next;
		}
	} 
	
	/**
	 * Checks to see if the stack is Full
	 * @return false - This will always return false
	 */
	public boolean isFull()
	{
		return false;
	}
	
	/**
	 * Checks to see if the stack is empty
	 * @return first == null - This will return true if the first Link is null
	 */
	public boolean isEmpty()
	{
		return first == null;
	}

}
