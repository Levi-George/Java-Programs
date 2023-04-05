package matrixFactorization;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MatrixFactorization {

	public static void main(String[] args) {
		ArrayList<ArrayList<Float> > A = new ArrayList<ArrayList<Float> >();
		ArrayList<ArrayList<Float> > P = new ArrayList<ArrayList<Float> >();
		ArrayList<ArrayList<Float> > L = new ArrayList<ArrayList<Float> >();
		ArrayList<ArrayList<Float> > U = new ArrayList<ArrayList<Float> >();
		
		int rows = 0, cols = 0;
		
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
		//System.out.print(readData.hasNextLine());
		if(readData.hasNextLine())
		{
			String data = readData.nextLine();
			//System.out.print(data);
			
			if(data.length() == 1)
			{
				rows = Integer.parseInt(data.split(" ")[0]);
				cols = Integer.parseInt(data.split(" ")[0]);
				//System.out.print(rows + " /" + cols); //DEBUG CODE
			}
			else
			{
				rows = Integer.parseInt(data.split(" ")[0]);
				cols = Integer.parseInt(data.split(" ")[1]);
				//System.out.print(rows + " -" + cols); //DEBUG CODE
			}
			
			
		}
		
		for(int i = 0; i < rows; i++)
		{
			A.add(new ArrayList<Float>());
		}
		
		//System.out.print("\nA - Columns " + A.size() + " \n"); //DEBUG CODE
		
		int currRow = 0;
		//get points
		while(readData.hasNextLine())
		{
			
			String data = readData.nextLine();
			for(int i = 0; i < data.split(" ").length; i++)
			{
				float nextElement = Float.parseFloat(data.split(" ")[i]);
				//System.out.print(nextElement + " "); //DEBUG CODE
				A.get(currRow).add(nextElement);
			}
			currRow++;
			//System.out.print("\n"); //DEBUG CODE
		}
	}
}
