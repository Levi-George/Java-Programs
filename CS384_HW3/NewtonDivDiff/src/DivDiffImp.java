import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DivDiffImp {
	
	//Global data structures for polynomial used for approximating and 
	//storing divided difference values
	public static ArrayList<ArrayList<Float> > DividedDiffValues = new ArrayList<ArrayList<Float> >();
	public static ArrayList<Float> polynomial = new ArrayList<Float>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//we need someDividedDiffValues to hold our x and y's, 
		//we also store our total points in the points variable
		int points;
		ArrayList<Float> x = new ArrayList<Float>();
		ArrayList<Float> y = new ArrayList<Float>();
		
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
		
		//get number of points
		if(readData.hasNextLine())
		{
			String data = readData.nextLine();
			points = Integer.parseInt(data);
		}
		
		//get points
		while(readData.hasNextLine())
		{
			String data = readData.nextLine();
			x.add(Float.parseFloat(data.split(" ")[0]));
			y.add(Float.parseFloat(data.split(" ")[1]));
		}
		
		//holds our choice and user input
		int choice = 0;
		Float input = 0.0f;
		
		//calculate the coefficients of our matrix
		coefficients(x,y);
		
		//display the menu and get our value back
		choice = menu();
		
		switch(choice)
		{
		case -1:
			return;//we get this for any errors
		case 1:
			//call the polynomial "calculator" to construct our polynomial
			calculator(x);
			break;
		case 2:
			//get user input
			System.out.println("Enter a value to approximate");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			//try to get user input
			try
			{
				input = Float.parseFloat(reader.readLine());
			}
			catch(Exception e)
			{
				System.out.println("An error has occurred, closing program");
			}
				
			//call the approximator to approximate the f(x) value for the x given as user input
			approximator(x, input);
			break;
		case 3:
			return;
		}
		
	}
	
	//shows menu and gets user input
	static int menu()
	{
		String readData = "";
		int choice = 0;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("What would you like to do?");
		System.out.println("1. Show Polynomial");
		System.out.println("2. Approximate Point for Polynomial");
		System.out.println("3. Exit");
		
		
		try
		{
			readData = reader.readLine();
			choice = Integer.parseInt(readData);
		}
		catch(Exception e)
		{
			System.out.println("An Issue has occurred.");
			return -1;
		}
		
		if(choice > 3 || choice < 1)
		{
			System.out.println("Bad input, quitting program");
			choice = -1;
		}
		
		
		
		return choice;
	}
	
	//approximator will calculate the approximate value of f(x) given any x
	static void approximator(ArrayList<Float> x, Float input)
	{	
		Float sum = 0.0f;
		Float multiSum = 0.0f;
		
		sum += polynomial.get(0);
		for(int i = 1; i < polynomial.size(); i++)
		{
			
			multiSum = polynomial.get(i);
			for(int j = 0; j < i; j++)
			{
				multiSum *= (input - x.get(j));
			}
			
			sum += multiSum;
		}
		
		System.out.println("Value: " + sum);
	}
	
	//displays the polynomial using the matrix we created to hold the values
	static void calculator(ArrayList<Float> x)
	{
		String polyOutput = "";
		for(int i = 0; i < polynomial.size(); i++)
		{
			
			polyOutput += polynomial.get(i).toString();
			
			if(i != 0)
			{
				
				for(int j = 0; j < i; j++)
				{
					polyOutput += "(x-" + x.get(j) + ")";
				}
			}
			if(i != polynomial.size()-1)
			{
				polyOutput += " + ";
			}
			
			polyOutput += " ";
			
		}
		
		System.out.println("\nP(x) = " + polyOutput);
		
		
	}
	
	//move key values from matrix into a small data structure
	static void transfer()
	{
		for(int i = 0; i < DividedDiffValues.size(); i++)
		{
			polynomial.add(DividedDiffValues.get(i).get(0));
		}
	}
	
	//generate the coefficients for our polynomial
	static void coefficients(ArrayList<Float> x, ArrayList<Float> y)
	{
		int rounds = x.size();
		DividedDiffValues.add(y); //first row
		
		for(int j = 0; j < rounds-1; j++)
		{
			DividedDiffValues.add(new ArrayList<Float>());
			
			for(int i = 1; i < DividedDiffValues.get(j).size(); i++)
			{
				//create each divided difference
				float divDiff = DividedDiffValues.get(j).get(i) - DividedDiffValues.get(j).get(i-1);
				float width = x.get(i+j) - x.get(i-1);
				divDiff = divDiff / width;
				
				//add the divided difference into the matrix
				DividedDiffValues.get(j+1).add(divDiff);
				
			}
		}
		
		
		
		transfer();
	}
	
}
