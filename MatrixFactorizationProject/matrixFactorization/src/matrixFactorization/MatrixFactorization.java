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
	
		
		ArrayList<ArrayList<Float> > A = new ArrayList<ArrayList<Float> >();//originally parsed matrix
		ArrayList<ArrayList<Float> > P = new ArrayList<ArrayList<Float> >();//permutation matrix
		ArrayList<ArrayList<Float> > L = new ArrayList<ArrayList<Float> >();//Lower-Triangle Matrix
		
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
			
			//if only one value, we set rows/cols to same value
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
		
		//populate each matrix with it's rows
		for(int i = 0; i < rows; i++)
		{
			A.add(new ArrayList<Float>());
			P.add(new ArrayList<Float>());
			L.add(new ArrayList<Float>());
		}
		
		//the row we are currently populating
		int currRow = 0;
		
		//get points, populate matrix A
		while(readData.hasNextLine())
		{
			//read data
			String data = readData.nextLine();
			
			//for the length of our split data we will iterate
			for(int i = 0; i < data.split(" ").length; i++)
			{
				//get the float from the string data
				float nextElement = Float.parseFloat(data.split(" ")[i]);
				
				//add the element to the matrices
				A.get(currRow).add(nextElement);
				P.get(currRow).add(0.0f);
				L.get(currRow).add(0.0f);
			}
			currRow++;
			
		}
		
		//PARSING COMPLETE - BELOW IS COMPUTATIONS
		
		//set P and L as identity matrices
		setP(P);
		setP(L);
		
		//perform gaussian elimination
		GaussianElim(A, P, L, rows, cols);
		
		//grand finale
		printMatrixFinale(P, L, A);

	}
	
	public static void GaussianElim(ArrayList<ArrayList<Float> > Q, ArrayList<ArrayList<Float> > R, ArrayList<ArrayList<Float> > S, int rows, int cols )
	{
		
		boolean swap = false;//swap rows on true
		int swapped = 0;//the original row position
		int swappee = 0;//the row to be swapped
		
		//for each row we check if we have the lowest value, then swap
		//after that we may perform vector subtraction
		for(int i = 0; i < rows-1; i++)
		{
			//the row we are checking is the row we are on
			swapped = i;
		
			//we need to find the row with the smallest value for the column we are doing
			swappee = findMin(Q, i);
		
			//if swappee is not equal to the current row then we need to swap the rows
			if(swappee != swapped && swappee > -1)
			{
				swap(Q, swapped, swappee);
				swap(R, swapped, swappee);
			}
				
			//for each row beneath the current
			for(int j = i+1; j < cols; j++)
			{
				//we subtract the current row from the next row and grab the coefficient
				float coeff = VectorSubtraction(Q, i, j);
				
				//add the coefficient to our lower triangle matrix
				S.get(j).set(i, coeff);
				
			}
			
			
			
		}
	}
	
	
	//swapped is the row that we are checking i, swappee is the row that is the smallest value.
	public static void swap(ArrayList<ArrayList<Float> > Q, int swapped, int swappee)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		
		temp.addAll(Q.get(swapped));
	
		Q.get(swapped).clear();
		
		Q.get(swapped).addAll(Q.get(swappee));
		
		Q.get(swappee).clear();
		
		Q.get(swappee).addAll(temp);
		
	}
	
	public static int findMin(ArrayList<ArrayList<Float> > Q, int column)
	{
		int rowToSwap = -1;
		float min = Math.abs(Q.get(column).get(column));

		for(int i = column; i < Q.size(); i++)
		{
	
			float newMin = Math.abs(Q.get(i).get(column));
			
			if(min > newMin && newMin != 0)
			{
				min = newMin;
				rowToSwap = i;
			}
				
			
		}
		
		return rowToSwap;
	}
	
	//subtract a row (nextRow) by the selected row (row), and the column we are on is used for each 
	//INPUT: a matrix Q which will have next Row subtracted by row
	//PROCESS: subtract one row, from the next row in a matrix (Q)
	//OUTPUT: return coefficient used for subtraction
	public static float VectorSubtraction(ArrayList<ArrayList<Float> > Q, int row, int nextRow)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		ArrayList<Float> temp2 = new ArrayList<Float>();
		
		temp.addAll(Q.get(row));
		temp2.addAll(Q.get(nextRow));
		
		float coeff = temp2.get(row) / temp.get(row);
		
		for(int i = 0; i < temp2.size(); i++)
		{
			temp2.set(i, temp2.get(i) - (temp.get(i) * coeff)  );
		}
		
		Q.get(nextRow).clear();
		Q.get(nextRow).addAll(temp2);
		
		return coeff;
	}
	
	//populate P matrix
	
	//INPUT: Matrix Q
	//PROCESS: Add values to make matrix identity matrix
	//OUTPUT: No value returned
	public static void setP(ArrayList<ArrayList<Float> > Q)
	{
		for(int i = 0; i < Q.size(); i++)
		{
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				//add 1 to matrix when along diagonal
				if(i == j)
				{
					Q.get(i).set(j, 1.0f);
				}
			}
		}
	}
	
	//INPUT: Matrix Q 
	//PROCESS: Print out each element in a matrix pattern
	//OUTPUT: No output
	public static void printMatrix(ArrayList<ArrayList<Float> > Q)
	{
		for(int i = 0; i < Q.size(); i++)
		{
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(Q.get(i).get(j) + " ");
			}
			
			System.out.print("\n");
		}
	}
	
	//INPUT: Q, R, S; all matrices to be printed to console
	//PROCESS: print out each matrix in order of Q, R, S with extra formatting
	//OUTPUT: no returned output, prints to console
	public static void printMatrixFinale(ArrayList<ArrayList<Float> > Q, ArrayList<ArrayList<Float> > R, ArrayList<ArrayList<Float> > S)
	{
		//run for number of rows
		for(int i = 0; i < Q.size(); i++)
		{
			//format and print row i of Q
			System.out.print("|");
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(Q.get(i).get(j) + " ");
			}
			System.out.print("|\t");
			
			//format and print row i of R
			System.out.print("|");
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(R.get(i).get(j) + " ");
			}
			System.out.print("|\t");
			
			
			//format and print row i of S
			System.out.print("|");
			for(int j = 0; j < Q.get(i).size(); j++)
			{
				System.out.print(S.get(i).get(j) + " ");
			}
			System.out.print("|");
			
			System.out.print("\n");
		}
	}
	
	//UNUSED
	//INPUT: Q, left matrix; R, right matrix, S, matrix which will be written to
	//PROCESS: simple matrix multiplication
	//OUTPUT: No value returned, S is manipulated w/o memory management
	public static void matrixMultiplication(ArrayList<ArrayList<Float> > Q, ArrayList<ArrayList<Float> > R, ArrayList<ArrayList<Float> > S)
	{
		ArrayList<Float> temp = new ArrayList<Float>();
		
		//display left and right matrices
		System.out.print(Q);
		System.out.print("\t\t");
		System.out.print(R);
		System.out.print("\t\t = \t\t");
		
		//temp var
		float AddToS = 0.0f;
		
		for(int i = 0; i < Q.size(); i++)//for every row of Q
		{
			for(int j = 0; j < Q.get(0).size(); j++)//and for every column of R
			{
				for(int k = 0; k < Q.get(0).size(); k++)//Multiply reach row element of Q by each column element of R
				{
					temp.add(Q.get(i).get(j) * R.get(j).get(i));
					
					
				}
				
				for(int l = 0; l < temp.size(); l++)//for each element in temp, add to sum
				{
					AddToS += temp.get(l);
				}
				S.get(i).set(j, AddToS);//insert sum into new matrix
			}
		}
		
		//display final output
		System.out.print(S);
		System.out.print("\n\n");
	}
	
	
	//INPUT: Q, matrix to be transposed
	//PROCESSING: transpose the matrix
	//OUTPUT: no output return, data is manipulated
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
