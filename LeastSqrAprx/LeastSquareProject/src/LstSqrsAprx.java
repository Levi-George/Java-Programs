
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
