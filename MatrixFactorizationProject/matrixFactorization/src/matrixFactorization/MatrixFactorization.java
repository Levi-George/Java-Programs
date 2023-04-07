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
		ArrayList<ArrayList<Float> > LU = new ArrayList<ArrayList<Float> >();
		
		
		
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
		if(readData.hasNextLine())
		{
			String data = readData.nextLine();
			//System.out.print(data);
			
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
		
		for(int i = 0; i < rows; i++)
		{
			A.add(new ArrayList<Float>());
			P.add(new ArrayList<Float>());
			U.add(new ArrayList<Float>());
			L.add(new ArrayList<Float>());
			LU.add(new ArrayList<Float>());
		}
		
		int currRow = 0;
		//get points
		while(readData.hasNextLine())
		{
			
			String data = readData.nextLine();
			for(int i = 0; i < data.split(" ").length; i++)
			{
				float nextElement = Float.parseFloat(data.split(" ")[i]);
				
				A.get(currRow).add(nextElement);
				P.get(currRow).add(0.0f);
				U.get(currRow).add(0.0f);
				L.get(currRow).add(0.0f);
				LU.get(currRow).add(0.0f);
			}
			currRow++;
			
		}
		
		//PARSING COMPLETE - BELOW IS COMPUTATIONS
		
		setP(P);
		setP(L);
		
		GaussianElim(A, P, L, rows, cols);
		

	}
	
	public static void GaussianElim(ArrayList<ArrayList<Float> > A, ArrayList<ArrayList<Float> > P, ArrayList<ArrayList<Float> > L, int rows, int cols )
	{
		//find min
		boolean swap = false;
		int swapped = 0;
		int swappee = 0;
		int j = 0;
		
		//for each row
		for(int i = 0; i < rows-1; i++)
		{
			
			swapped = i;
		
			//we need to find the row with the smallest value for the column we are doing
			swappee = findMin(A, i);
		
			//if swappee is not equal to the current row then we need to swap the rows
			if(swappee != swapped && swappee > -1)
			{
				swap(A, swapped, swappee);
				swap(P, swapped, swappee);
			}
				
			
			for(j = i+1; j < cols; j++)
			{
				float coeff = VectorSubtraction(A, i, j);
				
				L.get(j).set(i, coeff);
				
				//System.out.print("\n");
				//printMatrix(A);
				//System.out.print("\n");
			}
			
			
			
		}
	}
	
	
	//swapped is the row that we are checking i, swappee is the row that is the smallest value.
	public static void swap(ArrayList<ArrayList<Float> > A, int swapped, int swappee)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		
		temp.addAll(A.get(swapped));
	
		A.get(swapped).clear();
		
		A.get(swapped).addAll(A.get(swappee));
		
		A.get(swappee).clear();
		
		A.get(swappee).addAll(temp);
		
		
		
	}
	
	public static int findMin(ArrayList<ArrayList<Float> > A, int column)
	{
		int rowToSwap = -1;
		float min = Math.abs(A.get(column).get(column));

		for(int i = column; i < A.size(); i++)
		{
			//System.out.print(A.get(i).get(column) + " "); //DEBUG CODE
			float newMin = Math.abs(A.get(i).get(column));
			
			if(min > newMin && newMin != 0)
			{
				min = newMin;
				rowToSwap = i;
			}
				
			
		}
		
		return rowToSwap;
	}
	
	//subtract a row (nextRow) by the selected row (row), and the column we are on is used for each 
	public static float VectorSubtraction(ArrayList<ArrayList<Float> > A, int row, int nextRow)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		ArrayList<Float> temp2 = new ArrayList<Float>();
		
		temp.addAll(A.get(row));
		temp2.addAll(A.get(nextRow));
		
		float coeff = temp2.get(row) / temp.get(row);
		
		for(int i = 0; i < temp2.size(); i++)
		{
			temp2.set(i, temp2.get(i) - (temp.get(i) * coeff)  );
		}
		
		A.get(nextRow).clear();
		A.get(nextRow).addAll(temp2);
		
		return coeff;
	}
	
	//populate P matrix
	public static void setP(ArrayList<ArrayList<Float> > P)
	{
		for(int i = 0; i < P.size(); i++)
		{
			for(int j = 0; j < P.get(i).size(); j++)
			{
				if(i == j)
				{
					P.get(i).set(j, 1.0f);
				}
			}
		}
	}
	
	public static void printMatrix(ArrayList<ArrayList<Float> > P)
	{
		for(int i = 0; i < P.size(); i++)
		{
			for(int j = 0; j < P.get(i).size(); j++)
			{
				System.out.print(P.get(i).get(j) + " ");
			}
			
			System.out.print("\n");
		}
	}
	
	public static void printMatrixFinale(ArrayList<ArrayList<Float> > Q, ArrayList<ArrayList<Float> > R, ArrayList<ArrayList<Float> > S)
	{
		for(int i = 0; i < Q.size(); i++)
		{
			System.out.print("|");
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(Q.get(i).get(j) + " ");
			}
			System.out.print("|\t");
			
			System.out.print("|");
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(R.get(i).get(j) + " ");
			}
			System.out.print("|\t");
			
			System.out.print("|");
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(S.get(i).get(j) + " ");
			}
			System.out.print("|");
			
			System.out.print("\n");
		}
	}
	
	public static void matrixMultiplication(ArrayList<ArrayList<Float> > Q, ArrayList<ArrayList<Float> > R, ArrayList<ArrayList<Float> > S)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		
		
		System.out.print(Q);
		System.out.print("\t\t");
		System.out.print(R);
		System.out.print("\t\t = \t\t");
		
		float AddToS = 0.0f;
		
		for(int i = 0; i < Q.size(); i++)//for every row of Q
		{
			for(int j = 0; j < Q.get(0).size(); j++)//and for every column of R
			{
				for(int k = 0; k < Q.get(0).size(); k++)//Multiple reach row element of Q by each column element of R
				{
					temp.add(Q.get(i).get(j) * R.get(j).get(i));
					
					
				}
				for(int l = 0; l < temp.size(); l++)
				{
					AddToS += temp.get(l);
				}
				S.get(i).set(j, AddToS);
			}
		}
		
		
		System.out.print(S);
		System.out.print("\n\n");
	}
	
	public static void matrixTranspose(ArrayList<ArrayList<Float> > Q)
	{
		float temp = 0;
		for(int i = 0; i < Q.size(); i++)
		{
			for(int j = Q.get(i).size() - 1; j > 0; j--)
			{
				temp = Q.get(i).get(j);
				
				Q.get(i).set(j, Q.get(j).get(i));
				Q.get(j).set(i, temp);
				
				
				
			}
		}
	}

}
