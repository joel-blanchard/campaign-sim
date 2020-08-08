/**
 * The SocialFundraiser class has everything we need to emulate a social fundraiser for our program and it extends fundraiser
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
public class SocialFundraiser extends Fundraiser
{
	/*
	 * Full parameter constructor
	 * @param inLoc The location of the fundraiser
	 * @param inCandidate The candidate of the fundraiser
	 */
     public SocialFundraiser(String inLoc, Candidate inCandidate)
     {
          super(inLoc,inCandidate);
     }
     
	/*
	 * The toString function converts a fundraiser to a string usually for printing
	 * @return The string that represents the fundraiser
	 */
     public String toString()
     {
          return getCandidate().getName() + "'s social fundraiser takes place in: " + getLocation() + " and has " + getDonors() + " donors attending.\n";
     }
     
	/*
	 * The raiseMoney function is how to initiate a fundraiser
	 * @return The amount of money raised
	 */
     public int raiseMoney()
     {
          int total = super.raiseMoney();
          getCandidate().setMoneyMod(getCandidate().getMoneyMod() + .1);
          return total;
     }
     
}