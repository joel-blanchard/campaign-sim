/**
 * The Driver class is what we use to run our election
 * @author Joel Blanchard
 * @author David Johnson
 * @author Ara Cho
 */
import java.util.*;
import java.io.*;
public class Driver
{         
	private static ArrayList<Candidate> candidates = new ArrayList<Candidate>();
	//private static ArrayList<Candidate> testCandidates = new ArrayList<Candidate>(); Test line, please ignore
	private static ArrayList<String> locations = new ArrayList<String>();
	private static ArrayList<String> parties = new ArrayList<String>();
	private static ArrayList<ArrayList<Candidate>> partiesList = new ArrayList<ArrayList<Candidate>>();
	private static int t = 0; //To be used to format the top of the CSV
	
     public static void main(String[] args) throws FileNotFoundException
     {
          File cList = new File("Candidate.txt");
          File lList = new File("Locations.txt");
          Scanner cScanner = new Scanner(cList);
          
          int cCounter = 0;
          while(cScanner.hasNext())
          {
               String line = cScanner.nextLine();
               String[] token = line.split(",");
			
               candidates.add(new Candidate(token[0],token[2],token[1], Integer.parseInt(token[3]), Integer.parseInt(token[4])));
               boolean match = false;
               for (String party : parties)
               {
                    if(party.equals(token[1]))
                    {
                         match = true;
                    }
               }
               if (!match)
               {
                    parties.add(token[1]);
               }
               cCounter++;
          }
          cScanner.close();
          
          cScanner = new Scanner(lList);
          while(cScanner.hasNext())
          {
               locations.add(cScanner.nextLine());
          }
          cScanner.close();
          
          createList(candidates.get(0));
          
         Random rand = new Random();
          for (int x = 0; x < 365; x++)
          {
               if (x % 10 == 0 && x < 305)
               {
						 //String party = parties.get(rand.nextInt(10));
                         String party = parties.get(rand.nextInt(parties.size() - 1));
						 ArrayList<Candidate> thisDebate = new ArrayList<Candidate>();
                         for (Candidate c : candidates)
                         {
                              if (party.equals(c.getParty()))
                              {
                                   thisDebate.add(c);
                              }
                         }
                              
                         boolean done = false;
                         Debate primary = null;
                         while (!done)
                         {
                              try
                              {
                                   primary = new Debate(locations.get(rand.nextInt(locations.size())), thisDebate);
                                   done = true;
                              }
                              catch (TooLowInPollsException e)
                              {
                                   System.err.println(e.getMessage() + "\nSo, we are removing lowest candidate...BUT, WE SHOULDN'T GET HERE!!!");
                              }    
                         }
                         System.out.println(primary);
                         System.out.println(primary.declareWinner()); 
                         
                    
               }

               else if (x % 10 == 0)
               {
                    boolean done = false;
                    Debate pres = null;
                    ArrayList<Candidate> thisDebate = new ArrayList<Candidate>();
                    for (Candidate copy : candidates)
                    {
                         thisDebate.add(copy);
                    }
                    while (!done)
                    {
                         try
                         {
                              pres = new Debate(locations.get(rand.nextInt(locations.size())), thisDebate);
                              done = true;
                         }
                         catch (TooLowInPollsException e)
                         {
                              System.err.println(e.getMessage() + "\nSo, we are removing lowest candidate...");
                              Candidate low = thisDebate.get(0);
                              for (int c = 1; c < thisDebate.size(); c++)
                              {
                                   if (low.getMoney() > thisDebate.get(c).getMoney())
                                   {
                                        low = thisDebate.get(c);
                                   }
                              }
                              thisDebate.remove(low);
                         }    
                         
                    }
                    System.out.println(pres);
                    System.out.println(pres.declareWinner());
                    
               }
               else
               {  
                    for (Candidate current : candidates)
                    {
                         if ((x < 250 && rand.nextInt(10) > 6) || (x > 250 && rand.nextInt(10) < 6))
                         {
                              Fundraiser sf = null;
                              if (current.getFundPref() == 0)
                              {
                                   sf = new SocialFundraiser(locations.get(rand.nextInt(locations.size())),current);
                              }
                              else if (current.getFundPref() == 1)
                              {
                                   sf = new PhoneFundraiser(locations.get(rand.nextInt(locations.size())),current);
                              }
                              else if (current.getFundPref() == 2)
                              {
                                   try
                                   {
                                        sf = new PACFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                   }
                                   catch (TooLowInPollsException e)
                                   {
                                        System.err.println(e.getMessage() + "\n Creating a different fundraiser instead.");
                                        if (rand.nextBoolean())
                                        {
                                             sf = new SocialFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                        }
                                        else
                                        {
                                             sf = new PhoneFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                        }
                                   }
                              }
                              
                              System.out.println(sf + "\tRaised: $" + sf.raiseMoney());
						
	
                         }
                         else
                         {
                              try
                              {
                                   int select = rand.nextInt(3);
                                   Advertisement ad = null;
                                   if (select == 0)
                                   {
                                        Candidate target = current;
                                        while (current.equals(target))
                                        {
                                             target = candidates.get(rand.nextInt(candidates.size()));
                                        }
                                        ad = new AttackAdvertisement(current.getSlogan(), current, target);
                                   }
                                   else if (select == 1)
                                   {
                                        ad = new IssueBasedAdvertisement(current.getSlogan(), current);
                                   }
                                   else
                                   {
                                        ad = new TownHallAdvertisement(current.getSlogan(), current);
                                   }
                                   
                                   System.out.println(ad.run());
                                   System.out.println(ad);
                              }
                              catch (OutOfMoneyException e)
                              {
                                   Fundraiser sf = null;
                                   System.err.println(e.getMessage() + "\nCandidate couldn't afford their add, fundraising instead");
                                   if (current.getFundPref() == 0)
                                   {
                                        sf = new SocialFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                   }
                                   else if (current.getFundPref() == 1)
                                   {
                                        sf = new PhoneFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                   }
                                   else if (current.getFundPref() == 2)
                                   {
                                        try
                                        {
                                             sf = new PACFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                        }
                                        catch (TooLowInPollsException e1)
                                        {
                                             System.err.println(e1.getMessage() + "\n Creating a different fundraiser instead.");
                                             if (rand.nextBoolean())
                                             {
                                                  sf = new SocialFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                             }
                                             else
                                             {
                                                  sf = new PhoneFundraiser(locations.get(rand.nextInt(locations.size())),current);
                                             }
                                        }
                                   }
                                   System.out.println(sf + "\tRaised: $" + sf.raiseMoney());
						
                              }
                         }
                    }
               }
               Collections.sort(candidates); //Use Collections.sort to sort candidates
			
			for (ArrayList<Candidate> thisList : partiesList)
			{
				Collections.sort(thisList);
			}
			
			for (Candidate thisCandidate : candidates)
			{
				int partyCounter = 0;
				for (ArrayList<Candidate> thisList : partiesList)
				{
					if (thisList.contains(thisCandidate))
					{
						thisCandidate.evaluate(x, thisList, candidates);
					}
				}
			}
			
			if (x == 305)
			{
				
				
				ArrayList<Candidate> newCandidates = new ArrayList<Candidate>();
				for (ArrayList<Candidate> partyList : partiesList)
				{
					sort(partyList);
					
					int thisMoney = 0;
					for (Candidate thisCandidate : partyList)
					{
						thisMoney = thisMoney + thisCandidate.getMoney();
					}
					
					partyList.get(partyList.size() - 1).setMoney(thisMoney);
					
					newCandidates.add(partyList.get(partyList.size() - 1));
				}
				
				candidates = newCandidates;
				//testCandidates = newCandidates;
			}
			
			candidateToCsv(x);
          }
          
          Election finalPres = new Election("US President", candidates);
          System.out.println(finalPres);

          // for (Candidate thisCandidate : testCandidates)
          //{
		//	System.out.println(thisCandidate);
          //}
     }
	
	/*
	 * The sort function sorts the candidates in ascending order by the amount of money they have
	 * @param inCandidates The candidates you wish to sort
	 */
	public static void sort(ArrayList<Candidate> inCandidates)
	{
		Candidate temp = null;
		boolean noSwaps = false;
		while (noSwaps == false)
		{
			noSwaps = true;
			for (int x = 0; x < inCandidates.size() - 1; x++)
			{	
				if (inCandidates.get(x).getMoney() > inCandidates.get(x + 1).getMoney())
				{
					//Swap
					temp = inCandidates.get(x);
					inCandidates.set(x, inCandidates.get(x + 1));
					inCandidates.set(x + 1, temp);

					noSwaps = false;
				}	
			
				//Tie for money
				if (inCandidates.get(x).getMoney() == inCandidates.get(x + 1).getMoney())
				{
					if (inCandidates.get(x).getName().compareTo(inCandidates.get(x + 1).getName()) > 0)
					{
						//Swap
						temp = inCandidates.get(x);
						inCandidates.set(x, inCandidates.get(x + 1));
						inCandidates.set(x + 1, temp);

						noSwaps = false;
					}
				}
			}
		}
	}
	
	/*
	 * The createList function creates a list of lists that splits the candidates into parties
	 * @param The starter for recursion
	 */
	public static void createList(Candidate inCandidate)
	{
		if (partiesList.size() == 0)
		{
			ArrayList<Candidate> party = new ArrayList<Candidate>();
			party.add(inCandidate);
			partiesList.add(party);
			for (Candidate candidate : candidates)
			{
				if (candidate.getParty().equals(inCandidate.getParty()) && candidate.getName().compareTo(inCandidate.getName())!= 0)
				{
					party.add(candidate);
				}
				else if (candidate.getName().compareTo(inCandidate.getName())!= 0)
				{
					createList(candidate);
				}
			}
		}

		else
		{
			boolean newParty = true;
			for (ArrayList<Candidate> candidateList : partiesList) //Loop through all parties
			{
				if (candidateList.get(0).getParty().equals(inCandidate.getParty()))
				{
					newParty = false;
				}
			}

			if (newParty == true) //Create list of Candidates
			{
				ArrayList<Candidate> party = new ArrayList<Candidate>();
				partiesList.add(party);
				party.add(inCandidate);
				for (Candidate candidate : candidates)
				{
					if (candidate.getParty().equals(inCandidate.getParty()) && candidate.getName().compareTo(inCandidate.getName())!= 0)
					{
						party.add(candidate);
					}
					else if (candidate.getName().compareTo(inCandidate.getName())!= 0)
					{
						createList(candidate);
					}
				}
			}
		}
		for (ArrayList<Candidate> candList : partiesList)
		{
			sort(candList);
		}
	}
	
	/*
	 * The candidateToCsv function will writes stats about the candidates to a csv file as the end of each day for analysis
	 * @param day The day of year the election cycle is on
	 */
	public static void candidateToCsv(int day) throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("candidates.csv"),true));
		
		if (t == 0)
		{
			//pw.append("Day,Name,Party,Money,Fund Pref,Ad Pref\n");
		}
		
		for (Candidate thisCandidate : candidates)
		{
			
			pw.append(day + "," + thisCandidate.getName() + "," + thisCandidate.getParty() + "," + thisCandidate.getMoney() + "," + thisCandidate.getFundPref() + "," + thisCandidate.getAdPref() + "\n");
		}
		
		t++;
		pw.close();
	}
}
