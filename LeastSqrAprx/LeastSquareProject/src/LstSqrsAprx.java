
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
		ArrayList<Float> polyA = new ArrayList<Float>();
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
		
		int i = 0;
		while(readData.hasNextLine())
		{
			String data = readData.nextLine();
			
			
			if(i == 0)
			{
				deg = Integer.parseInt(data.split(" ")[0]);
				N = Integer.parseInt(data.split(" ")[1]);
				
			}
			
			if(i == 1)
			{
				for(int j = 0; j < data.split(" ").length; j++)
				{
					x.add(Float.parseFloat(data.split(" ")[j]));
				}
			}
			
			if(i == 2)
			{
				for(int j = 0; j < data.split(" ").length; j++)
				{
					y.add(Float.parseFloat(data.split(" ")[j]));
				}
			}
			
			i++;
		}
		
		//outputParsed(deg, N, y ,x);
		
		LineAprox(y, x, linA, N);
		PolyAprox(y, x, normalMatrix, N, deg);
		
		
		
	}
	
	public static void outputParsed(int deg, int N, ArrayList<Float> y, ArrayList<Float> x )
	{
		System.out.println(deg + " " + N);
		System.out.println(y);
		System.out.println(x);
	}
	
	//
	public static void LineAprox(ArrayList<Float> y, ArrayList<Float> x, ArrayList<Float> linA, int N)
	{
		float a_0 = 0.0f;
		float a_1 = 0.0f;
		
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
		
		a_0 = ((xSqr * ySum) - (xySum * xSum)) / ((N*xSqr) - xSum);
		a_1 = ((N * xySum) - (xSum*ySum)) / ((N * xSqr) - (xSum*xSum));
		
		System.out.println("Linear \"Polynomial\": " + a_1 + "x" + " + " + a_0);
		
	}
	
	//P_n(x) = a_n x^n + ... + a_1 x^1 + a_0
	public static void PolyAprox(ArrayList<Float> y, ArrayList<Float> x, ArrayList<ArrayList<Float>> normalMatrix, int N, int deg)
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
			
			xy.add( sumYnXpow(y, x, i) );
			
		}
		
		System.out.println(normalMatrix);
		
		
		//perform Gaussian Elimination (didn't we do this in the last homework? That has made this much easier)
		
		for(int i = 0; i < normalMatrix.get(0).size()-1; i++)
		{		
			//for each row beneath the current
			for(int j = i+1; j < normalMatrix.size(); j++)
			{
				//we subtract the current row from the next row and grab the coefficient
				VectorSubtraction(normalMatrix, i, j);
				
			}
			
		}
		
		System.out.print(normalMatrix);
		System.out.print("\n" + xy);
		//
		
		//solve for each a_i
		for(int i = 0; i < normalMatrix.get(0).size()-1; i++)
		{		
			//for each row beneath the current
			for(int j = 0; j < normalMatrix.size(); j++)
			{
				
				
			}
			
		}
		
		
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
