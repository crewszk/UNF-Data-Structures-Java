/**
 * This class is a Country class. It holds the data of a country in an object such as its name, country code, capitol city, population, gdp per capita
 * and happiness rank.
 * 
 * @author Zackery Crews
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

    Country(String name, String code, String capitol, int population, long gdp, int happinessRank)
    {
        this.name = name;
        this.code = code;
        this.capitol = capitol;
        this.population = population;
        this.gdp = gdp;
        this.happinessRank = happinessRank;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCapitol(String capitol)
    {
        this.capitol = capitol;
    }

    public String getCapitol()
    {
        return this.capitol;
    }

    public void setPopulation(long population)
    {
        this.population = population;
    }

    public long getPopulation()
    {
        return this.population;
    }

    public void setGDP(long gdp)
    {
        this.gdp = gdp;
    }

    public long getGDP()
    {
        return this.gdp;
    }

    public void setHappinessRank(int happinessRank)
    {
        this.happinessRank = happinessRank;
    }

    public int getHappinessRank()
    {
        return this.happinessRank;
    }

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

    public void printCountryReport()
    {
        System.out.printf("%-40s%-17s%-17s%-15d%-20d%-16d\n", this.name, this.code, this.capitol, this.population, this.gdp, this.happinessRank);
    }
    
    public void printCountry()
    {
    	System.out.printf("%-20s%s\n%-20s%s\n%-20s%s\n%-20s%d\n%-20s%d\n%-20s%d\n\n", "Name:", this.name, "Code:", this.code, "Capitol:", this.capitol, "Population:", this.population, "GDP:", this.gdp, "Happiness Rank:", this.happinessRank);
    }
}