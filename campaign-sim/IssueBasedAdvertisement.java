/**
 * The IssueBasedAdvertisment class has everything we need to emulate a IssueBasedAdvertisement for our program and it extends advertisement
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
public class IssueBasedAdvertisement extends Advertisement
{
     
	/*
	 * Full parameter constructor
	 * @param inMsg The message of the advertisement
	 * @param inCand The candidate the ad is about
	 */
     public IssueBasedAdvertisement(String inMsg, Candidate inCand) throws OutOfMoneyException
     {
          super(inMsg,inCand);
          setCost(rand.nextInt(15001) + 5000);
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
          return "This Issue-Based Advert is:\n\tFor " + getCandidate().getName() + "\n\tAbout: " + getMessage() + "\n\tCosts: $" + getCost();
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
               getCandidate().setDebateMod(getCandidate().getDebateMod() + .1);
               return "My name is " + getCandidate().getName() + ", and I approve this message";
          }
          else
          {
               getCandidate().setDebateMod(getCandidate().getDebateMod() + .05);
               return "This message has not been approved by " + getCandidate().getName() + ".";
          }
     }
    

}