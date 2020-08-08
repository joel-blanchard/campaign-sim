/**
 * The Fundraiser class has everything we need to emulate a fundraiser for our program
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
import java.util.Random;
public class Fundraiser
{
     private String location = "DEFAULT";
     private Candidate candidate = null;
     private int donors;
     private Random rand = new Random();
     
	/*
	 * Full parameter constructor
	 * @param inLoc The location of the fundraiser
	 * @param inCandidate The candidate whos fundraiser it is
	 */
     public Fundraiser(String inLoc, Candidate inCandidate)
     {
          setLocation(inLoc);
          setCandidate(inCandidate);
          donors = rand.nextInt(201);
     }
     
	/*
	 * The toString function converts a fundraiser to a string usually for printing
	 * @return The string that represents the fundraiser
	 */
     public String toString()
     {
          return candidate.getName() + "'s fundraiser takes place in: " + getLocation() + " and has " + getDonors() + " donors attending.\n";
     }
     
	/*
	 * The setLocation function sets the location for the fundraiser
	 * @param inLoc The location of the fundraiser
	 */
     public void setLocation(String inLoc)
     {
          location = inLoc;
     }
     
	/*
	 * The getLocation function returns the location of the fundraiser
	 * @return The location of the fundraiser
	 */
     public String getLocation()
     {
          return location;
     }
     
	/*
	 * The setCandidate function sets the candidate of the fundraiser
	 * @param inCandidate the candidate for the fundraiser
	 */
     public void setCandidate(Candidate inCandidate)
     {
          candidate = inCandidate;
     }
     
	/*
	 * The getCandidate function returns the candidate of the fundraiser
	 * @return The candidate of the fundraiser
	 */
     public Candidate getCandidate()
     {
          return candidate;
     }
     
	/*
	 * The getDonors function returns the amount of donors of the fundraiser
	 * @return The amount of donors
	 */
     public int getDonors()
     {
          return donors;
     }
     
	/*
	 * The raiseMoney function is how to initiate a fundraiser
	 * @return The amount of money raised
	 */
     public int raiseMoney()
     {
          int total = 0;
          for (int x = 0; x < donors; x++)
          {
               total += rand.nextInt(151);
          }
          candidate.addMoney((int)(total * candidate.getMoneyMod()));
          return total;
     }
}