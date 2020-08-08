/**
 * The AttackAdvertisment class has everything we need to emulate a AttackAdvertisement for our program it extends advertisement
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
public class AttackAdvertisement extends Advertisement
{
     private Candidate target = null;
     
	/*
	 * Full parameter constructor
	 * @param inMsg The message of the advertisement
	 * @param inCand The candidate the ad is about
	 * @param inTarget The target of the ad
	 */
     public AttackAdvertisement(String inMsg, Candidate inCand, Candidate inTarget) throws OutOfMoneyException
     {
          super(inMsg,inCand);
          setCost(rand.nextInt(25001) + 50000);
          target = inTarget;
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
          return "This Attack Advert is:\n\tFor " + getCandidate().getName() + "\n\tAgainst: " + target.getName() + "\n\tAbout: " + getMessage() + "\n\tCosts: $" + getCost();
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
               getCandidate().setDebateMod(getCandidate().getDebateMod() + .2);
               getCandidate().setMoneyMod(getCandidate().getMoneyMod() - .2);
               target.setDebateMod(target.getDebateMod() - .15);
               target.setMoneyMod(target.getMoneyMod() - .25);
               return "My name is " + getCandidate().getName() + ", and I approve this message";
          }
          else
          {
               getCandidate().setDebateMod(getCandidate().getDebateMod() + .1);
               getCandidate().setMoneyMod(getCandidate().getMoneyMod() - .05);
               target.setDebateMod(target.getDebateMod() - .05);
               target.setMoneyMod(target.getMoneyMod() - .1);
               return "This message has not been approved by " + getCandidate().getName() + ".";
          }
     }
}