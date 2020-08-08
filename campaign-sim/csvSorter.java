import java.util.*;
import java.io.*;
public class csvSorter
{
	public static void main(String[] args)
	{
		File inFile = new File("candidates.csv");
		File outFile = new File("formattedCandidates.csv");
		
		boolean selectFile = false; //We may want to have an option for a different file name, if so make true
		if (selectFile)
		{
			Scanner inScanner = new Scanner(System.in);
			System.out.print("Enter File Name: ");
			String input = inScanner.nextLine();
			
			inFile = new File(input);
			
			inScanner.close();
		}
		
		Scanner fileScanner = null;
		try
		{
			fileScanner = new Scanner(inFile);
		}
		catch (FileNotFoundException u)
		{
			System.out.println("error");
		}
		ArrayList<String[]> allEntries = new ArrayList<String[]>(); //All Lines
		
		while (fileScanner.hasNext())
		{
			String line = fileScanner.nextLine();
			String[] tokens = line.split(",");
			
			allEntries.add(tokens);
		}
		
		Hashtable candidates = new Hashtable();
		
		for (String[] thisEntry : allEntries)
		{
			if (!candidates.containsKey(thisEntry[1]))
			{
				candidates.put(thisEntry[1], new ArrayList<String[]>());
			}
			
			((ArrayList<String[]>)candidates.get(thisEntry[1])).add(thisEntry);
		}
		
		ArrayList<String> keys = new ArrayList<String>();
		
		Enumeration e = candidates.keys();
		
		while(e.hasMoreElements())
		{
			keys.add((String)e.nextElement());
		}
		
		PrintWriter pw = null;
		boolean done = false;
		try
		{
			pw = new PrintWriter(outFile);
			done = true;
		}
		catch (FileNotFoundException u)
		{
			System.out.println("error");
		}
		
		if (!done)
		{
			System.exit(0);
		}
		
		ArrayList<String> output = new ArrayList<String>();
		
		for (String key : keys)
		{	
			for (String[] thisDay : (ArrayList<String[]>)candidates.get(key)) //Each day
			{
				String line = "";
				int v = 0; //Our counter
				for (String thisString : thisDay)
				{
					if (v == thisDay.length - 1)
					{
						line = line + thisDay[v];
					}
					else
					{
						line = line + thisDay[v] + ",";
					}
					v++;
				}
				line = line + "\n";
				output.add(line);
			}
			output.add("\n");
		}
		
		for (String thisLine : output)
		{
			pw.write(thisLine);
		}
		
		pw.close();
	}
}