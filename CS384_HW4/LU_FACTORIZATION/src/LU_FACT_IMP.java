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
		int swapped = 0;
		int swappee = 0;
		
		//for each row
		for(int i = 0; i < rows-1; i++)
		{
			for(int j = 0; j < cols-1; j++)
			{
				swapped = i;
			
				//we need to find the row with the smallest value for the column we are doing
				swappee = findMin(A, j, i);
			
				//if swappee is not equal to the current row then we need to swap the rows
				if(swappee != i)
				{
					swap(A, swapped, swappee);
				}
				
				//then we need to subtract the rows below the current row.
				float coefficient = 0.0f;
				coefficient = A.get(swappee).get(j) / A.get(swapped).get(j);
				VectorSubtraction(A, coefficient, swapped, swappee, j);
			
			}
		}
	}
	
	//swapped is the row that we are checking i, swappee is the row that is the smallest value.
	public static void swap(ArrayList<ArrayList<Float> > A, int swapped, int swappee)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		
		temp = A.get(swapped);
	
		A.get(swapped).addAll(swapped, A.get(swappee)); 
		
		A.get(swappee).addAll(temp);
		
		
		
	}
	
	public static int findMin(ArrayList<ArrayList<Float> > A, int column, int row)
	{
		int rowToSwap = row;
		float min = 0;
		for(int i = 0; i < A.size(); i++)
		{
			min = A.get(i).get(column);
			
			// i is arbitrary for this, we can replace it with any value, we just need a column length
			//but we only need to do one column
			for(int j = column; j < A.get(i).size(); j++)
			{
				if(min > A.get(i).get(j))
				{
					min = A.get(i).get(j);
					rowToSwap = i;
				}
				
			}
		}
		
		return rowToSwap;
	}
	
	//subtract each row by the first one for that iteration
	public static void VectorSubtraction(ArrayList<ArrayList<Float> > A, float coefficient, int row, int nextRow, int col)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		ArrayList<Float> temp2 = new ArrayList<Float>();
		
		temp.addAll(A.get(row));
		temp2.addAll(A.get(nextRow));
		
		for(int i = 0; i < temp.size(); i++)
		{
			temp2.set(i, temp2.get(i) - temp.get(i)*coefficient);
		}
	
		A.set(nextRow, temp2);
		
	}
	
	//populate P matrix
	public static void setP(ArrayList<ArrayList<Float> > P, int row, int col)
	{
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(i == j)
				{
					P.get(i).set(j, 1.0f);
				}
			}
		}
	}

}
