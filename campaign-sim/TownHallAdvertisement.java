/**
 * The Fundraiser class has everything we need to emulate a fundraiser for our program
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
public class TownHallAdvertisement extends Advertisement
{
     private int attendees = 0;
     
	/*
	 * Full parameter constructor
	 * @param inMsg The message of the advertisement
	 * @param inCand The candidate the ad is about
	 */
     public TownHallAdvertisement(String inMsg, Candidate inCand) throws OutOfMoneyException
     {
          super(inMsg,inCand);
          setCost(rand.nextInt(95001) + 5000);
          attendees = rand.nextInt(151);
          if (getCost() > getCandidate().getMoney())
          {
               throw new OutOfMoneyException();
          }

     }
     
	/*
	 * The toString function converts a fundraiser to a string usually for printing
	 * @return The string that represents the fundraiser
	 */
     public String toString()
     {
          return "This Town Hall Advert is:\n\tFor " + getCandidate().getName() + "\n\tAbout: " + getMessage() + "\n\tCosts: $" + getCost();
     }
     
	/*
	 * The run function runs the ad
	 * @return The endorsement message of the candidate
	 */
     public String run()
     {
          if (getCandidate().getMoney() < getCost())
          {
               return "NO CASH";
          }
          
          getCandidate().addMoney(getCost() * -1);
          
          if (getCandidate().endorse(this))
          {
               getCandidate().setMoneyMod(getCandidate().getMoneyMod() + (attendees / 500.0));
               return getCandidate().getName() + " holds a successfull Town Hall.";
          }
          else
          {
               getCandidate().setMoneyMod(getCandidate().getMoneyMod() + (attendees / 2000.0));
               return getCandidate().getName() + " holds a Town Hall.";
          }
     }
}