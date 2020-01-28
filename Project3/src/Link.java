/**
 * This class is a Link class that is a basis for links in a linked list. It holds
 * data and can be used in a singly linked list with only next, or a double linked list
 * with both next and previous pointers. * 
 * @author Zackery Crews - n00826481
 * @version 10-21-2019
 */
public class Link {

	private Country data;
	Link next;
	Link previous;
	
	Link(Country data)
	{
		this.data = new Country(data.getName(), data.getCode(), data.getCapitol(), data.getPopulation(), data.getGDP(), data.getHappinessRank());
	}
	
	/**
	 * This returns the data held by this specific Link
	 * @return an object of type Country
	 */
	public Country get()
	{
		return this.data;
	}
}
