/**
 * This is a Priority class. It creates a priority queue based on the size given and will be able to
 * insert Country objects into the array, and remove them based on priority.
 * 
 * @author 		Zackery Crews - n00826481
 * @version		10-04-2019
 */
public class Priority {

	private Country[] queue;
	private int size;
	
	Priority(int size)
	{
		queue = new Country[size];
		this.size = 0;
	}
	
	/**
	 * This method inserts the provided country object into the priority queue
	 * @param input - the provided country object
	 * 
	 * AUTHOR NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!! AUTHOR NOTE
	 * Java really did not like me just using the parameter like 'queue[this.size++] = input;' as it was overwriting the
	 * entire queue, every single element, with the supplied object for each instance. So instead I cloned the object
	 * with a new one called 'output' like below and it fixed this issue. All correspondance with people who have already
	 * taken this class couldn't figure out why this bug existed either.
	 */
	public void insert(Country input)
	{
		
		if(isFull())
		{
			System.out.println("ERROR, PRIORITY QUEUE FULL");
		}
		else
		{
			Country output = new Country(input.getName(), input.getCode(), input.getCapitol(), input.getPopulation(), input.getGDP(), input.getHappinessRank());
			queue[this.size++] = output;
		}
	}
	
	/**
	 * This method removes a country object from the queue based on priority. Priority is determined by the 
	 * object with the most GDP per Capita. It will then replace the removed object with the one at the top of 
	 * the array and reduce its size
	 * @return the country object removed from the 
	 */
	public Country remove()
	{
		if(isEmpty())
		{
			System.out.println("ERROR, PRIORITY QUEUE EMPTY, RETURNING NULL");
		}
		else
		{			
			int max = getMax(this.queue, this.size);
			Country output = queue[max];
			
			queue[max] = queue[--this.size];
			
			return output;
		}
		
		return null;
	}
	
	/**
	 * This method will print the entire queue in a table format while including the GDP per Capita
	 */
	public void printQueue()
	{
		System.out.printf("%-40s%-17s%-17s%-15s%-20s%-16s%-14s\n", "Country Name", "Country Code", "Capitol City", "Population", "GDP", "Happiness Rank", "GDP Per Capita");		
		for(int i = this.size - 1; i >= 0; i--)
		{
			queue[i].printCountryReport();
			System.out.printf("%-14.0f\n", (float)(queue[i].getGDP() / queue[i].getPopulation()));
		}
	}
	
	/**
	 * THis method checks to see if the priority queue is empty
	 * @return a boolean true or false based on if the queue is empty
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
	 * This method checks to see if the priority queue is full
	 * @return a boolean true or false based on if the queue is full
	 */
	public boolean isFull()
	{
		boolean output;
		
		if(this.size == queue.length)
			output = true;
		else
			output = false;
		
		return output;
	}
	
	/**
	 * This static method takes in the supplied country array and its current size, and then returns the index
	 * of the object that has the highest GDP per Capita
	 * 
	 * @param queue - an array of Country objects
	 * @param size - the current size of the supplied queue array
	 * @return an integer that is the index of the highest gdp per capita for a given country array
	 */
	public static int getMax(Country[] queue, int size)
	{
		int max = 0;
		
		for(int i = 1; i < size - 1; i++)
		{
			if((queue[i].getGDP() / queue[i].getPopulation()) > (queue[max].getGDP() / queue[max].getPopulation()))
				max = i;
		}
		
		return max;
	}
}
