/**
 * The Advertisement class has everything we need to emulate an advertisement for our program
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
import java.util.Random;
public class Advertisement
{
     private String message = "Nothing";
     private Candidate cand = null;
     private int cost = 0;
     protected Random rand = new Random();
     
	/*
	 * Full parameter constructor
	 * @param inMsg The message of the advertisement
	 * @param inCand The candidate the ad is about
	 */
     public Advertisement(String inMsg, Candidate inCand) throws OutOfMoneyException
     {
          setCandidate(inCand);
          setMessage(inMsg);
          if (cost > inCand.getMoney())
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
          return "This generic Advert is:\n\tFor " + cand.getName() + "\n\tAbout: " + getMessage() + "\n\tCosts: $" + getCost();
     }
     
	/*
	 * The getMessage function returns the message of the ad
	 * @return The message of the ad
	 */
     public String getMessage()
     {
          return message;
     }
     
	/*
	 * The setMessage function sets the message of the ad
	 * @param newMessage The new message of the ad
	 */
     protected void setMessage(String newMessage)
     {
          message = newMessage;
     }
     
	/*
	 * The getCandidate function returns the candidate of the ad
	 * @return The candidate of the ad
	 */
     public Candidate getCandidate()
     {
          return cand;
     }
     
	/*
	 * The setCandidate function sets the candidate of the ad
	 * @param newCand The candidate you wish to set
	 */
     protected void setCandidate(Candidate newCand)
     {
          cand = newCand;
     }
     
	/*
	 * The getCost function returns the cost of the ad
	 * @return The cost of the ad
	 */
     public int getCost()
     {
          return cost;
     }
     
	/*
	 * The setCost function sets the cost of the ad
	 * @param inCost The new cost of the ad
	 */
     protected void setCost(int inCost)
     {
          if (inCost > 0)
          {
               cost = inCost;
          }
     }
     
	/*
	 * The run function runs the ad
	 * @return returns a string that says SOMETHING
	 */
     public String run()
     {
          getCandidate().addMoney(getCost() * -1);
          return "SOMETHING!";
     }

}