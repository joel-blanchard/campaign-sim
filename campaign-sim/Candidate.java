/**
 * The Candidate class is everything we need to emulate a candidate in our election
 * @author Joel Blanchard
 * @author David Johnson
 * @author Ara Cho
 */
import java.util.Random;
import java.util.ArrayList;
public class Candidate implements Comparable<Candidate>
{
     private String name = "DEFAULT";
     private String slogan = "DEFAULT";
     private int money = 0;
     private static long allMoney = 0;
     private String party = "DEFAULT";
     private Random rand = new Random();
     private int adPref = -1; // 0: IssueBased, 1: Attack, 2:TownHall
     private int fundPref = -1; // 0: Social, 1: RotoCall, 2:PACs
     private double moneyMod = 1.0;
     private double debateMod = 1.0;

	/*
	 * The toString function converts our candidate to a string usually for printing
	 * @return The string that represents the candidate
	 */
     public String toString()
     {
          String adType = "";
          if (getAdPref() == 0)
          {
               adType = "Issue Based";
          }
          else if (getAdPref() == 1)
          {
               adType = "Attack";
          }
          else if (getAdPref() == 2)
          {
               adType = "Town Hall";
          }
          else
          {
               adType = "Unknown";
          }
          
          String fundType = "";
          if (getAdPref() == 0)
          {
               fundType = "Social Events";
          }
          else if (getAdPref() == 1)
          {
               fundType = "Automated Phone Calls";
          }
          else if (getAdPref() == 2)
          {
               fundType = "Political Action Committees";
          }
          else
          {
               fundType = "Unknown";
          }
          
          return "Candidate " + getName() + ":\n\tSlogan: " + getSlogan() + "\n\tParty: " + getParty() + "\n\tCurrent funds: " + getMoney() + "\n\tPreferred Ad Type: " + adType + "\n\tPreferred Fund Type: " + fundType + "\n\tCurrent Money Modifier: " + getMoneyMod() + "\n\tCurrent Debate Modifier: " + getDebateMod() + "\n\tMoney: " + getMoney() + "\n";
     }
     
	/*
	 * Full Parameter constructor
	 * @param inName The name of the candidate
	 * @param inSlogan The slogan for the candidate
	 * @param inparty The candidates party
	 * @param newAdPref The ad preference of the candidate
	 * @param newFundPref The Fundraiser preference of the candidate
	 */
     public Candidate(String inName, String inSlogan, String inParty, int newAdPref, int newFundPref)
     {
          setName(inName);
          setSlogan(inSlogan);
          setParty(inParty);
          addMoney(rand.nextInt(101));
          setAdPref(newAdPref);
          setFundPref(newFundPref);
     }
	/*
	 * Half Parameter Constructr
	 * @param inName The name of the candidate
	 * @param inSlogan The slogan for the candidate
	 * @param inparty The candidates party
	 */
	public Candidate(String inName, String inSlogan, String inParty)
     {
          setName(inName);
          setSlogan(inSlogan);
          setParty(inParty);
          addMoney(rand.nextInt(101));
          setAdPref(rand.nextInt(2));
          setFundPref(rand.nextInt(2));
     }
	
     /*
	 * The setName function sets the name of the candidate
	 * @param inName The name you wish to set
	 */
     public void setName(String inName)
     {
          name = inName;
     }
     
	/*
	 * The getName function gets the name of the candidate
	 * @return The name of the candidate
	 */
     public String getName()
     {
          return name;
     }
     
	/*
	 * The setSlogan function sets the slogan of the candidate
	 * @param inSlogan The slogan you wish to set for the candidate
	 */
     public void setSlogan(String inSlogan)
     {
          slogan = inSlogan;
     }
     
	/*
	 * The getSlogan function gets the slogan of the candidate
	 * @return The slogan of the candidate
	 */
     public String getSlogan()
     {
          return slogan;
     }
     
	/*
	 * The setParty function sets the party of the candidate
	 * @param inParty The party you wish to set for the candidate
	 */
     public void setParty(String inParty)
     {
          party = inParty;
     }
     
	/*
	 * The getParty function returns the party of the candidate
	 * @return The party of the candidate
	 */
     public String getParty()
     {
          return party;
     }
     
	/*
	 * The getMoney function returns the money for the candidate
	 * @return the candidates money
	 */
     public int getMoney()
     {
          return money;
     }
     
	/*
	 * The getAllMoney function returns the money for all candidates
	 * @return All candidates money combined
	 */
     public static long getAllMoney()
     {
          return allMoney;
     }
     
	/*
	 * The addMoney functin adds an amount of money to the existing amount
	 * @param newMoney The amount of money you wish to add
	 */
     public void addMoney(int newMoney)
     {
          money += newMoney;
          allMoney +=newMoney;
     }
     
	/*
	 * The setMoney function sets and overrides the money of the candidate
	 * @param newMoney The amount of you wish to set the candidates money variable to
	 */
	public void setMoney(int newMoney)
	{
		money = newMoney;
	}
	
	/*
	 * The endorse function endorses an advertisement if it is of the right kind
	 * @param newAd The advertisement to check
	 * @return whether or not the ad is endorsed
	 */
     public boolean endorse(Advertisement newAd)
     {
          return (getAdPref() == 0 && newAd instanceof IssueBasedAdvertisement) || (getAdPref() == 1 && newAd instanceof AttackAdvertisement) || (getAdPref() == 2 && newAd instanceof TownHallAdvertisement);
     }
     
	/*
	 * The equals function checks to see if one candidate is equal to another
	 * @param otherCand The candidate you wish to check against
	 * @return Whether or not the candidate is equal
	 */
     public boolean equals(Candidate otherCand)
     {
          return getName().equals(otherCand.getName());
     }
     
	/*
	 * The compareTo function comapres candidates names and will return 1, 0, or -1 depending on the order of the alphabet
	 * @param otherCand The candidate you wish to comapre to
	 * @return integer value of less greater or equal (-1, 0, 1)
	 */
	public int compareTo(Candidate otherCand)
	{
		if (getMoney() - otherCand.getMoney() == 0)
		{
			return getName().compareTo(otherCand.getName());
		}
		else
		{
			return getMoney() - otherCand.getMoney();
		}
     }
     
	/*
	 * The getAdPref function returns the advertisement preference of the candidate
	 * @return The ad preference of the candidate
	 */
     public int getAdPref()
     {
          return adPref;
     }
     
	/*
	 * The setAdPref function sets the candidates ad preference
	 * @param newPref the preference you wish to set
	 */
     private void setAdPref(int newPref)
     {
          adPref = newPref;
     }
     
	/*
	 * The getFundPref function returns the fundraiser preference of the candidate
	 * @return The fundraiser preference of the candidate
	 */
     public int getFundPref()
     {
          return fundPref;
     }
     
	/*
	 * The setFundPref function sets the fundraiser preference of the candidate
	 * @param newPref The new preference
	 */
     private void setFundPref(int newPref)
     {
          fundPref = newPref;
     }
     
	/*
	 * The getMoneyMod function returns the money modifier of the candidate
	 * @return The money modifier of the candidate
	 */
     public double getMoneyMod()
     {
          if (moneyMod < 0)
          {
               return 0;
          }
          return moneyMod;
     }
     
	/*
	 * The setMoneyMod function sets the money modifier of the candidate
	 * @param newMod The new money modifier
	 */
     public void setMoneyMod(double newMod)
     {
          moneyMod = newMod;
     }

	/*
	 * The getDebateMod function returns the debate modifier of the candidate
	 * @return the debate modifier
	 */
     public double getDebateMod()
     {
          if (debateMod < 0)
          {
               return 0;
          }
          return debateMod;
     }
     
	/*
	 * The setDebateMod function sets the debate modifier of the candidate
	 * @param newMod The new debate modifier
	 */
     public void setDebateMod(double newMod)
     {
          debateMod = newMod;
     }

	public void evaluate(int day, ArrayList<Candidate> partyList, ArrayList<Candidate> candidates)
	{
		if (day < 305) //Compare to Candidates in party
		{
			if (getMoney() < .1 * (double)partyList.get(partyList.size() - 1).getMoney());//Candidate too low in polls compared to party members
			{
				setAdPref(rand.nextInt(3)); //Generate random int 0-2 and set it as adPref
				setFundPref(rand.nextInt(3)); //Generate random int 0-2 and set it as fundPref
			}
		}
		else if (day >= 305) //Compare to all Candidates
		{
			if (getMoney() < .1 * (double)candidates.get(candidates.size() - 1).getMoney())//Candidate too low in polls compared to all Candidates
			{
				setAdPref(rand.nextInt(3)); //Generate random int 0-2 and set it as adPref
				setFundPref(rand.nextInt(3)); //Generate random int 0-2 and set it as fundPref
			}
		}
	


	}
}









