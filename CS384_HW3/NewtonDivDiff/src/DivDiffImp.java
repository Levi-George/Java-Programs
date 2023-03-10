import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DivDiffImp {
	
	public static ArrayList<ArrayList<Float> > thing = new ArrayList<ArrayList<Float> >();
	public static ArrayList<Float> polynomial = new ArrayList<Float>();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//we need something to hold our x and y's
		int points;
		ArrayList<Float> x = new ArrayList<Float>();
		ArrayList<Float> y = new ArrayList<Float>();
		
		Scanner readData = null;
		File dataFile = null;

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
		
		
		int choice = 0;
		
		coefficients(x,y);
		
		choice = menu();
		
		switch(choice)
		{
		case -1:
			return;
		case 1:
			calculator(x, y);
		case 2:
			approximator(x, y);
		case 3:
			return;
		}
		
		
		
	}
	
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
			System.out.println("Bad input, quitting program");
		
		choice = -1;
		
		return choice;
	}
	
	static void approximator(ArrayList<Float> x, ArrayList<Float> y, Float input)
	{
		
		
		
		
	}
	
	static void calculator(ArrayList<Float> x, ArrayList<Float> y)
	{
		
		
		
	}
	static void coefficients(ArrayList<Float> x, ArrayList<Float> y)
	{
		int rounds = x.size();
		thing.add(y); //first row
		ArrayList<Float> temp = new ArrayList<Float>();
		
		for(int j = 0; j < rounds; j++)
		{
			for(int i = 0; i < thing.get(j).size()-1; i++)
			{
				float divDiff = thing.get(j).get(i+1) - thing.get(j).get(i);
				divDiff = divDiff / (x.get((i+1)+j) - x.get(i));
				temp.add(divDiff);
			}
			
		}
		
	}
	
}
