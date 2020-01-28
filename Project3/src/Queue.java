/**
 * This class is a Queue class that utilizes a Doubly Linked List of Country objects
 * as a data structure. It can create a Queue of any size needed, and can add data
 * to the front or back, remove data from the front or back, find a specific element
 * by its Country name and delete it, and print the entire Queue.
 * @author Zackery Crews - n00826481
 * @version 10-25-2019
 */

public class Queue {

	Link first;
	Link last;
	
	Queue(){ }
	
	/**
	 * This method inserts an element of type Country into the end of the queue
	 * @param data - an object of type Country
	 */
	public void insertEnd(Country data)
	{
		if(isFull())
			System.out.println("ERROR, QUEUE IS FULL");
		else
		{
			Link l = new Link(new Country(data.getName(), data.getCode(), data.getCapitol(), data.getPopulation(), data.getGDP(), data.getHappinessRank()));
			if(isEmpty())
			{
				last = l;
				first = l;
			}
			else
			{
				last.next = l;
				l.previous = last;
				last = l;
			}			
		}
	}
	
	/**
	 * This method inserts an element of type Country into the front of the queue
	 * @param data - an object of type Country
	 */
	public void insertFront(Country data)
	{
		if(isFull())
			System.out.println("ERROR, QUEUE IS FULL");
		else
		{
			Link l = new Link(new Country(data.getName(), data.getCode(), data.getCapitol(), data.getPopulation(), data.getGDP(), data.getHappinessRank()));
			if(isEmpty())
			{
				last = l;
				first = l;
			}
			else
			{
				first.previous = l;
				l.next = first;
				first = l;
			}
		}
	}
	
	/**
	 * This method removes an element of type Country from the end of the Queue and returns
	 * the specific object removed
	 * @return output - an Object of type Country
	 */
	public Country removeEnd()
	{
		if(isEmpty())
			System.out.println("ERROR, QUEUE IS EMPTY");
		else
		{
			Country output = last.get();
			last = last.previous;
			
			if(last == first)
			{
				last = null;
				first = null;
			}
			return output;
		}
		return null;
	}
	
	/**
	 * This method removes an element of type Country from the front of the queue and returns
	 * the specific element removed
	 * @return output - an object of type Country
	 */
	public Country removeFront()
	{
		if(isEmpty())
			System.out.println("ERROR, QUEUE IS EMPTY");
		else 
		{
			Country output = first.get();
			first = first.next;
			
			if(first == last)
			{
				first = null;
				last = null;
			}
			return output;
		}
		
		return null;
	}
	
	/**
	 * This method will find a specific element based on its Countries name, delete the element
	 * from the queue, and then return true or false if it found it
	 * @param input - a String value of a Country objects name to be found
	 * @return outputs if the Country object was found in the Queue
	 */
	public boolean findDelete(String input)
	{
		Link current = first;
		while(current.get().getName() != input)
		{
			if(current.next == null)
				return false;
			else
				current = current.next;
		}
		
		if(current == first)
			first = current.next;
		else
			current.previous.next = current.next;
		if(current == last)
			last = current.previous;
		else
			current.next.previous = current.previous;
		
		return true;		
	}
	
	/**
	 * This method walks through the queue and prints every element starting from
	 * the front, and printing the data in a formated table until null is reached 
	 */
	public void printQueue()
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
	 * This method checks to see if the Queue is empty
	 * @return a boolean value on whether first and last are null
	 */
	public boolean isEmpty()
	{
		return first == null && last == null;
	}
	
	/**
	 * This method checks to see if the Queue is full
	 * @return always returns false
	 */
	public boolean isFull()
	{
		return false;
	}

}
