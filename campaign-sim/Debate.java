/**
 * The Debate class has everything we need to emulate a debate for our program
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
import java.util.*;
public class Debate 
{
     private String location = "DEFAULT";
     private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
     private Random rand = new Random();
     
	/*
	 * Full parameter constructor
	 * @param inLoc The location of the debate
	 * @param newList The list of candidates
	 */
     public Debate(String inLoc, ArrayList<Candidate> newList) throws TooLowInPollsException
     {
          setLocation(inLoc);
          setCandidates(newList);
     }
     
	/*
	 * The toString function converts a fundraiser to a string usually for printing
	 * @return The string that represents the fundraiser
	 */
     public String toString()
     {
          return "This debate takes place at " + getLocation() + ".  The Candidates participating are:\n" + getCandidates() + "\n";
     }
     
	/*
	 * The setLocation function sets the location of the debate
	 * @param inLoc The location of the debate
	 */
     public void setLocation(String inLoc)
     {
          location = inLoc;
     }
     
	/*
	 * The getLocation function returns the location of the debate
	 * @return The location of the debate
	 */
     public String getLocation()
     {
          return location;
     }
     
	/*
	 * The setCandidate funcion sets the candidates of the debate
	 * @param newList The new list of candidates
	 */
     public void setCandidates(ArrayList<Candidate> newList) 
     {
          //If using list mode, reset old list
          candidates.clear();
          
          //Go through new List
          for (Candidate newCand : newList)
          {
               //Add each newCandidate
			   if(newCand.getMoney() >= newCand.getAllMoney() * .05)//set enough money
			   {
					addCandidate(newCand);
			   }
			   else
			   {
				   //throw new TooLowInPollsException();
			   }
		  }
     }
     
     /*
	 * The addCandidate function adds a candidate to the debate
	 * @param newCand The candidate you wish to add
	 */
     public void addCandidate(Candidate newCand)
     {
          //Verify no dupes before adding the new Candidate
          boolean noMatch = true;
          for (Candidate current : candidates)
          {
               if (newCand.equals(current))
               {
                    noMatch = false;
               }
          }
          if (noMatch)
          {
               candidates.add(newCand);
          }
     }
     
	/*
	 * The getCandidates function returns the candidates in the debate
	 * @return The candidates of the debate
	 */
     public String getCandidates()
     {
          String candList = "";
          for (Candidate cand : candidates)
          {
               candList = candList + "\n" + cand;
          }
          return candList;
     }
     
	/*
	 * The declareWinner function delcares the winner of the debate
	 * @return The winner of the debate
	 */
     public String declareWinner()
     {
          if (candidates.size() == 0)
          {
               return "NO WINNER";
          }
          //Points array
          int[] points = new int[candidates.size()];
          int total = 0;
          for (int x = 0; x < candidates.size(); x++)
          {
               points[x] = (int) (rand.nextInt(101) * candidates.get(x).getDebateMod());
               total += points[x];
          }
          
          //Determine Winner!
          int winnerIndex = -1;
          int maxPoints = -1;
          for (int x = 0; x < points.length; x++)
          {
               if (points[x] > maxPoints || (points[x] == maxPoints && rand.nextBoolean()) )
               {
                    maxPoints = points[x];
                    winnerIndex = x;
               }
          }          
          String output = "The winner of this debate is " + candidates.get(winnerIndex).getName() + ".  This winning candidate recieved the following payouts:";          
          
          //Handle payouts
          int payouts = 0;
          for (int x = 0; x < candidates.size(); x++)
          {
               if (x != winnerIndex)
               {
                    int payout = (candidates.get(x).getMoney() * (total - points[x])) / total;
                    payouts += payout;
                    candidates.get(x).addMoney(-1 * payout);
                    candidates.get(winnerIndex).addMoney(payout);
                    output = output + "\n\t" + candidates.get(x).getName() + " paid out $" + payout;
               }
          }
          
          return output;
          
     }
     
}


















