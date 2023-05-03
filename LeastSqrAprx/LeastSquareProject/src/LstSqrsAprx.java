/***
Author: Levi George
Date:	04/29/23
Class:	CS384 - Numerical Analysis
Assn:	To perform linear and polynomial approximation given
			a degree for the polynomial/linear function
			a number of points to approximate with
			and a set of x values with corresponding y values
*/



import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.math.*;

public class LstSqrsAprx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//polynomial degree and N for points
		int deg = 0;
		int N = 0;
		ArrayList<Float> x = new ArrayList<Float>();
		ArrayList<Float> y = new ArrayList<Float>();
		ArrayList<Float> linA = new ArrayList<Float>();
		
		ArrayList<ArrayList<Float>> normalMatrix = new ArrayList<ArrayList<Float>>();
		
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
		
		//parse our data
		int i = 0;//parsing state variable
		while(readData.hasNextLine())
		{
			String data = readData.nextLine();
			
			//get our degree and number of points
			if(i == 0)
			{
				deg = Integer.parseInt(data.split(" ")[0]);
				N = Integer.parseInt(data.split(" ")[1]);
				
			}
			
			//get our x points
			if(i == 1)
			{
				for(int j = 0; j < data.split(" ").length; j++)
				{
					x.add(Float.parseFloat(data.split(" ")[j]));
				}
			}
			
			//get our f(x) points
			if(i == 2)
			{
				for(int j = 0; j < data.split(" ").length; j++)
				{
					y.add(Float.parseFloat(data.split(" ")[j]));
				}
			}
			
			i++;
		}
		
		//debug function
		//outputParsed(deg, N, y ,x);
		
		//our polynomial coefficients
		ArrayList<Float> P_x;
		
		//if our degree is too low, we just quit
		if( deg < 0)
			return;
		
		//if our degree is less than 2 (a_1 * x + a_0)
		//we do a linear approximation
		if(deg < 2)
		{
			P_x = LineAprox(y, x, linA, N);
		}
		//otherwise we do a polynomial approximation
		else
		{
			P_x = PolyAprox(y, x, normalMatrix, N, deg);
		}
		
		//print the polynomial values as per instructions
		for(int j = 0; j < P_x.size(); j++)
		{
			System.out.print("a" + j + " = " + P_x.get(j) + "\t");
		}
		
		//display the error as per instruction
		System.out.println("\nError: " + error(y, x, P_x));
		
		//System.out.println(P_x);
	}
	
	//E = sum(N, (y - P(x))^2 )
	public static float error(ArrayList<Float> y, ArrayList<Float> x, ArrayList<Float> a)
	{
		float error = 0.0f;
		
		for(int i = 0; i < x.size(); i++)
		{
			float P_x = 0.0f;
			
			for(int j = 0; j < a.size(); j++)
			{
				P_x += Math.pow(x.get(i), j) * a.get(j);
			}
			
			float yMinx = y.get(i) - P_x;
			
			error += Math.pow(yMinx, 2);
		}
		
		
		return error;
	}
	
	public static void outputParsed(int deg, int N, ArrayList<Float> y, ArrayList<Float> x )
	{
		System.out.println(deg + " " + N);
		System.out.println(y);
		System.out.println(x);
	}
	
	//
	public static ArrayList<Float> LineAprox(ArrayList<Float> y, ArrayList<Float> x, ArrayList<Float> linA, int N)
	{
		float a_0 = 0.0f;
		float a_1 = 0.0f;
		
		ArrayList<Float> a = new ArrayList<Float>();
		
		float xSqr = 0.0f;
		float ySum = 0.0f;
		float xySum = 0.0f;
		float xSum = 0.0f;
		
		for(int i = 0; i < x.size(); i++)
		{
			xSqr += x.get(i) * x.get(i);
			ySum += y.get(i);
			xySum += y.get(i) * x.get(i);
			xSum += x.get(i);
		}
		
		a.add( ((xSqr * ySum)  - (xySum * xSum)) / ((N * xSqr) -  xSum) );
		a.add(    ((N * xySum) -  (xSum * ySum)) / ((N * xSqr) - (xSum*xSum)) );
		
		System.out.println("Linear \"Polynomial\": " + a_1 + "x" + " + " + a_0);
		
		return a;
		
	}
	
	//P_n(x) = a_n x^n + ... + a_1 x^1 + a_0
	public static ArrayList<Float> PolyAprox(ArrayList<Float> y, ArrayList<Float> x, ArrayList<ArrayList<Float>> normalMatrix, int N, int deg)
	{
		ArrayList<Float> xy = new ArrayList<Float>();
		ArrayList<Float> a = new ArrayList<Float>();
		//form the matrix based on our x and y
		
		
		
		for(int i = 0; i < deg+1; i++)
		{
			normalMatrix.add(new ArrayList<Float>());
			
			//add the primary x^i power rows
			for(int j = 0; j < deg+1; j++)
			{
				normalMatrix.get(i).add(sumPow(x, j+i));
			}
			
			normalMatrix.get(i).add(sumYnXpow(y, x, i));
			//xy.add(  );
			
		}
		
		//System.out.println(normalMatrix);
		
		
		//perform Gaussian Elimination (didn't we do this in the last homework? That has made this much easier)
		
		for(int i = 0; i < normalMatrix.get(0).size()-1; i++)
		{	
			
			int swapped = 0;//the original row position
			int swappee = 0;//the row to be swapped
			
			//the row we are checking is the row we are on
			swapped = i;
		
			//we need to find the row with the smallest value for the column we are doing
			swappee = findMin(normalMatrix, i);
		
			//if swappee is not equal to the current row then we need to swap the rows
			if(swappee != swapped && swappee > -1)
			{
				swap(normalMatrix, swapped, swappee);
			}
			
			
			//for each row beneath the current
			for(int j = i+1; j < normalMatrix.size(); j++)
			{
				//we subtract the current row from the next row and grab the coefficient
				VectorSubtraction(normalMatrix, i, j);
				
			}
			
		}
		
		//System.out.println(normalMatrix);
		//System.out.print("\n" + xy);
		//
		
		int lastIndex = normalMatrix.get(0).size() - 1;
		
		//remove a from normal matrix
		for(int i = 0; i < normalMatrix.get(0).size(); i++)
		{
			a.add(normalMatrix.get(i).remove(lastIndex));
		}
		
		
		//System.out.println(normalMatrix);
		
		//solve for each a_i
		for(int i = normalMatrix.get(0).size()-1; i > -1; i--)
		{		
			
			int rowSize = normalMatrix.get(0).size();
			float solvedA = 0.0f;
			solvedA = a.get(i);
			
			//for each row beneath the current
			for(int j = normalMatrix.size() - 1; j > -1; j--)
			{	
				if(j == i)
				{
					solvedA = solvedA / normalMatrix.get(i).get(j);
				}
				else
				{
					solvedA -= normalMatrix.get(i).get(j) * a.get(j);
				}
			}
			
			a.set(i, solvedA);
			
		}
		
		
		//System.out.print(a);
		return a;
		
	}
	
	public static void VectorSubtraction(ArrayList<ArrayList<Float> > Q, int row, int nextRow)
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
		
		return;
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
	
	public static float sumPow(ArrayList<Float> x, int pow)
	{
		float sumSqr = 0.0f;
		
		
		for(int i = 0; i < x.size(); i++)
		{
			sumSqr += Math.pow(x.get(i), pow);
		}
		
		return sumSqr;
	}
	
	public static float sumYnXpow(ArrayList<Float> y, ArrayList<Float> x, int iter)
	{
		float multiSum = 0.0f;
		
		for(int i = 0; i < x.size(); i++)
		{
			multiSum += y.get(i) * (float)Math.pow(x.get(i), iter);
		}
		
		return multiSum;
	}

}
