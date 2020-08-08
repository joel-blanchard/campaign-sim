/**
 * The Election class has everything we need to emulate a election for our program
 * @author David Johnson
 * @author Joel Blanchard
 * @author Ara Cho
 */
import java.util.*;
public class Election
{
     private String title = "DEFAULT";
     private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
     private Random rand = new Random();
     
	/*
	 * Full parameter constructor
	 * @param inTitle The title of the election
	 * @param newList The list of candidates
	 */
     public Election(String inTitle, ArrayList<Candidate> newList)
     {
          setTitle(inTitle);
          setCandidates(newList);
     }
     
	/*
	 * The toString function converts a fundraiser to a string usually for printing
	 * @return The string that represents the fundraiser
	 */
     public String toString()
     {
          return "This is the " + getTitle() + " election.  The Candidates participating are:\n" + getCandidates() + "\n" + declareWinner() + "\n";
     }
     
	/*
	 * The setTitle function sets the title of the election
	 * @param inTitle The title of the election
	 */
     public void setTitle(String inTitle)
     {
          title = inTitle;
     }
     
	/*
	 * The getTitle function returns the title of the debate
	 * @return The title of the debate
	 */
     public String getTitle()
     {
          return title;
     }
     
	/*
	 * The setCandidates function sets the candidates of the election
	 * @param newList The list of candidates
	 */
     public void setCandidates(ArrayList<Candidate> newList)
     {
          //If using list mode, reset old list
          candidates.clear();
          
          //Go through new List
          for (Candidate newCand : newList)
          {
               //Add each newCandidate
               addCandidate(newCand);
          }
     }
     
     /*
	 * The addCandidate function adds a candidate to the election
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
	 * The getCandidates function returns the candidate of the election
	 * @return the Candidates of the election
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
	 * The declareWinner function is how we determine the winner
	 * @return The winner of the election
	 */
     public String declareWinner()
     {
          Candidate winner = candidates.get(0);
          for (int x = 1; x < candidates.size(); x++)
          {
               if ( (winner.getMoney() < candidates.get(x).getMoney()) || (rand.nextBoolean() && winner.getMoney() == candidates.get(x).getMoney()) )
               {
                    winner = candidates.get(x);
               }
          }
          
          return winner.getName() + " is the winner of the " + getTitle() + " election";
     }
     
}