/**
 * This class is a Country class. It holds the data of a country in an object such as its name, country code, capitol city, population, gdp
 * and happiness rank.
 * 
 * @author Zackery Crews - n00826481
 * @version 09-15-2019
 */

public class Country {

    private String name;
    private String code;
    private String capitol;
    private long population;
    private long gdp;
    private int happinessRank;

    Country()
    {

    }

    Country(String name, String code, String capitol, long population, long gdp, int happinessRank)
    {
        this.name = name;
        this.code = code;
        this.capitol = capitol;
        this.population = population;
        this.gdp = gdp;
        this.happinessRank = happinessRank;
    }

/**
 * This method sets the attribute name as the parameter name
 * 
 * @param name - the string to be set as the attribute name
 */
    public void setName(String name)
    {
        this.name = name;
    }

/**
 * This method returns the attribute name
 * @return name - the string attribute
 */
    public String getName()
    {
        return this.name;
    }

/**
 * This method sets the attribute code as the parameter code
 * @param code - the string to be set as the attribute code
 */
    public void setCode(String code)
    {
        this.code = code;
    }

/**
 * This method returns the attribute code
 * @return code - the string attribute
 */
    public String getCode()
    {
        return this.code;
    }

/**
 * This method sets the attribute capitol as the parameter capitol
 * @param capitol - the string to be set as the attribute capitol
 */
    public void setCapitol(String capitol)
    {
        this.capitol = capitol;
    }

/**
 * This method returns the attribute capitol
 * @return capitol - the string attribute
 */
    public String getCapitol()
    {
        return this.capitol;
    }

/**
 * This method sets the attribute population as the parameter population
 * @param population - the long variable to be set as the attribute population
 */
    public void setPopulation(long population)
    {
        this.population = population;
    }

/**
 * This method returns the attribute population
 * @return population - the long attribute
 */
    public long getPopulation()
    {
        return this.population;
    }

/**
 * This method sets the attribute gdp with as the parameter gdp
 * @param gdp - the long variable to be set as the attribute gdp
 */
    public void setGDP(long gdp)
    {
        this.gdp = gdp;
    }

/**
 * This method returns the long attribute gdp
 * @return gdp - the long attribute
 */
    public long getGDP()
    {
        return this.gdp;
    }

/**
 * This method sets the happinessRank attribute as the parameter happinessRank 
 * @param happinessRank - the integer variable to be set as the attribute happinessRank
 */
    public void setHappinessRank(int happinessRank)
    {
        this.happinessRank = happinessRank;
    }

/**
 * This method returns the integer attribute happinessRank
 * @return happinessRank - the int attribute
 */
    public int getHappinessRank()
    {
        return this.happinessRank;
    }

/**
 * The next 5 methods all compare the attribute of the object inserted into the method with the attribute of the
 * current object instance
 * @param input - the country object used for comparison
 * @return a boolean true or false depending on if the attributes equal to each other of not
 */
    public boolean compareName(Country input)
    {
        if(this.name == input.getName())
        {
            return true;
        }

        return false;
    }

    public boolean compareCode(Country input)
    {
        if(this.code == input.getCode())
        {
            return true;
        }
        
        return false;
    }

    public boolean compareCapitol(Country input)
    {
        if(this.capitol == input.getCapitol())
        {
            return true;
        }

        return false;
    }

    public boolean comparePopulation(Country input)
    {
        if(this.population == input.getPopulation())
        {
            return true;
        }

        return false;
    }

    public boolean compareGDP(Country input)
    {
        if(this.gdp == input.getGDP())
        {
            return true;
        }

        return false;
    }

//This method prints the countries attributes in a table format
    public void printCountryReport()
    {
        System.out.printf("%-40s%-17s%-17s%-15d%-20d%-16d", this.name, this.code, this.capitol, this.population, this.gdp, this.happinessRank);
    }
    
//This method prints the countries attributes as a singular form
    public void printCountry()
    {
    	System.out.printf("%-20s%s\n%-20s%s\n%-20s%s\n%-20s%d\n%-20s%d\n%-20s%d\n\n", "Name:", this.name, "Code:", this.code, "Capitol:", this.capitol, "Population:", this.population, "GDP:", this.gdp, "Happiness Rank:", this.happinessRank);
    }
}