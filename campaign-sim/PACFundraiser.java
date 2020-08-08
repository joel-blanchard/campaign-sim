/**
 * The PacFundraiser class has everything we need to emulate a fundraiser for our program and it inherits fundraiser
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
public class PACFundraiser extends Fundraiser
{
	/*
	 * Full parameter constructor
	 * @param inLoc The location of the fundraiser
	 * @param inCandidate The candidate of the fundraiser
	 */
     public PACFundraiser(String inLoc, Candidate inCandidate) throws TooLowInPollsException
     {
          super(inLoc,inCandidate);
          long total = Candidate.getAllMoney();
          if (inCandidate.getMoney() < total * .01)
          {
               throw new TooLowInPollsException();
          }
     }
     
	/*
	 * The toString function converts a fundraiser to a string usually for printing
	 * @return The string that represents the fundraiser
	 */
     public String toString()
     {
          return getCandidate().getName() + "'s PAC fundraiser takes place in: " + getLocation() + " and has " + getDonors() + " donors attending.\n";
     }
     
	/*
	 * The raiseMoney function is how to initiate a fundraiser
	 * @return The amount of money raised
	 */
     public int raiseMoney()
     {
          int total = super.raiseMoney();
          getCandidate().setMoneyMod(getCandidate().getMoneyMod() + .2);
          getCandidate().setDebateMod(getCandidate().getDebateMod() + .1);
          return total;
     }
     
}