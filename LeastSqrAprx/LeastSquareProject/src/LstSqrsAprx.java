
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
