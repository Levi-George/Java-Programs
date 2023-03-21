import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class LU_FACT_IMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<ArrayList<Float> > A = new ArrayList<ArrayList<Float> >();
		ArrayList<ArrayList<Float> > P = new ArrayList<ArrayList<Float> >();
		ArrayList<ArrayList<Float> > L = new ArrayList<ArrayList<Float> >();
		ArrayList<ArrayList<Float> > U = new ArrayList<ArrayList<Float> >();
		
		int rows, cols = 0;
		
		//we prepare out reader and file
		Scanner readData = null;
		File dataFile = null;
		
		//we try to open the file and make our scanner
		try
		{
			dataFile = new File("data.txt");
			readData = new Scanner(dataFile);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("data.txt not found");
		}
		
		//get number of rows and columns (assume in that order)
		if(readData.hasNextLine())
		{
			String data = readData.nextLine();
			
			if(data.length() == 1)
			{
				rows = Integer.parseInt(data.split(" ")[0]);
				cols = Integer.parseInt(data.split(" ")[0]);
			}
			else
			{
				rows = Integer.parseInt(data.split(" ")[0]);
				cols = Integer.parseInt(data.split(" ")[1]);
			}
			
			
		}
		
		for(int i = 0; i < cols; i++)
		{
			A.add(new ArrayList<Float>());
		}
		
		//get points
		while(readData.hasNextLine())
		{
			String data = readData.nextLine();
			for(int i = 0; i < data.split(" ").length; i++)
			{
				float nextElement = Float.parseFloat(data.split(" ")[i]);
				A.get(i).add(nextElement);
			}
		}
		
		
		
		
	}
	
	public static void GaussianElim(ArrayList<ArrayList<Float> > A, ArrayList<ArrayList<Float> > P, int rows, int cols )
	{
		//find min
		boolean swap = false;
		
		for(int i = 0; i < rows-1; i++)
		{
			float min = 0;
			for(int j = 0; j < rows-1; j++)
			{
				//find p (an integer between i and p that is not zero)
				if(min > A.get(i).get(j))
				{
					min = A.get(i).get(j);
					//when do we flag swap?
				}
				
				
				
			}
			
			
		}
	}

}
