package hornersMethod;
import java.io.*;
import java.util.LinkedList;
import java.lang.String;
import java.math.*;

//P(x) = (x-x_0)Q(x) + b0
		//b0 = P(x_0)
		//Q(x) = P'(x)
		//b_k = a_k + b_(k+1)*x_0
		//Q(x_0) = P'(x_0)

//AUTHOR: - LEVI GEORGE
//DATE: 02-19-2023
//CLASS: CS384 - Numerical Analysis
//PURPOSE: Calculate and get closer to 0 of the function based on our starting point and and polynomial given.
public class HornersMethod {

	public static void main(String[] args) {
		
		//Parsing Stage ****************************************************
		
		inputValidation(args);//validate user input
		
		//linked list - based on each X, it will have our node address, coefficient and power
		LinkedList<Float> P_of_x = new LinkedList<Float>();
		float x_0 = Float.parseFloat(args[args.length-1]); //get last string and convert it to int 
		
		int lastOrder = 0;
		int i = 0;
		
		//TODO add code for larger gaps between each term [This should work now]
		//parse out our values into the DS
		while(i+1 != args.length)//check next space of nothing, so we don't OOB
		{
			//get the coefficient
			float coeffToAdd = Float.parseFloat(args[i]);
			
			//add zero to coeff list when a gap between powers exists (i.e. x^4 + x^2)
			while((lastOrder - (Float.parseFloat(args[i+1]))) > 1 )
			{
				lastOrder--;
				P_of_x.addLast((float)0.0);
			}
			
			//add the actual coeff
			P_of_x.addLast(coeffToAdd);
			
			//set last order as the current order
			lastOrder = Integer.parseInt(args[i+1]); //get the power of the coefficient
			
			//move ahead in the args array
			i += 2;
		}
		
		//print the cofficients as a polynomial
		printCoeff(P_of_x);
		
		//End of Parsing / Start of Actual Algorithm **********************************
		
		float error = 1;
		float x_next = x_0;
		float x_prev = x_0;
		int j = 0;
		
		//We will use this LinkedList for the algorithm directly from the book below.
		LinkedList<Float> Q_of_x = new LinkedList<Float>();
		LinkedList<Float> R_of_x = new LinkedList<Float>();
		
		//iterating Horner's method
		while(error > Math.pow(10, -4) && j < 10)
		{
			//get Q_of_X from P, then pop P(X_0) off Q of x
			Q_of_x = calculate_QofX(P_of_x, x_next);
			float P_x = Q_of_x.remove(Q_of_x.size()-1);
			
			//get R_of_X from Q, then pop Q(X_0) off R of x
			R_of_x = calculate_QofX(Q_of_x, x_next);
			float Q_x = R_of_x.remove(R_of_x.size()-1);
			
			//save last x value
			x_prev = x_next;
			//calculate next x value
			x_next = x_next - P_x/Q_x;
			
			//output our calculation
			System.out.println("x_next = " + x_next + " = " + x_prev + " - " + P_x + " / " + Q_x);
			
			//output our error calculation (as we approach our zero, we want to find how close we are)
			error = Math.abs(0 - input_x_0(P_of_x, x_prev));
			System.out.println("error = " + error + " = " + 0 + " - " + input_x_0(P_of_x, x_prev) + "\n");
			
			
			j++;
		}
		
		
	}
	
	//***************	END OF MAIN - START OF FUNCS
	
	//INPUT: linked list of polynomial
	//PROCESSING: output each coefficient into the form polynomial
	//OUTPUT: no output, just print to console
	static void printCoeff(LinkedList<Float> f_of_x)
	{
		
		for(int i = 0; i < f_of_x.size(); i++)
		{
			System.out.print("" + f_of_x.get(i) + "x^" + (f_of_x.size() - (i+1)) + " ");
		}
		System.out.print("\n");
		
		
	}
	
	//INPUT: input args from main
	//PROCESSING: check length and verify everything works for inputs
	//OUTPUT: no output, return if function does not have proper inputs
	static void inputValidation(String[] args)
	{
		if(args.length == 0 )
		{
			System.out.println("Please enter a valid number of inputs");
			return;
		}
		else if((args.length % 2) != 1)
		{
			System.out.println("Invalid number of arguments, it will be odd.");
			return;
		}
	}
	
	//INPUT: Original linked list and starting point
	//PROCESSING: Output each part of our calculation, then calculate the Q of x function
	//OUTPUT: return new Q of X polynomial as a linked list
	static LinkedList<Float> calculate_QofX(LinkedList<Float> f_of_x, float x_0)
	{
		LinkedList<Float> Q_of_x = new LinkedList<Float>();
		
		//b_k = a_k + b_k+1 * x_0
		float b_k =0;
		float b_k1 = 0;
		
		//output each coefficient of p of x
		System.out.print("" + x_0 + " | ");
		for(int i = 0; i < f_of_x.size(); i++)
		{
			System.out.print(" a_" + (f_of_x.size()-i) + " = " + f_of_x.get(i) + "        ");
		}
		System.out.print("\n");
		
		//calculate and add each coefficient to Q of x, 
		for(int i = 0; i < f_of_x.size(); i++)
		{
			b_k = f_of_x.get(i) + (b_k1 * x_0);
			System.out.print("   " + "          " + b_k1*x_0 + " ");
			
			Q_of_x.addLast(b_k);
			
			b_k1 = b_k;
		}
		System.out.print("\n");
		
		//print Q of x to the console
		for(int i = 0; i < Q_of_x.size(); i++)
		{
			System.out.print("        b_" + (Q_of_x.size()-i) + " = " + Q_of_x.get(i) + " ");
		}
		System.out.print("\n\n");
		
		return Q_of_x;
	}
	
	//INPUT: Linked List of our Polynomial and starting point we are given for that polynomial's zero
	//PROCESSING: directly calculate x_0 across each polynomial term
	//OUTPUT: the value we get by summing each term's
	//Calculate f of x for our function directly.
	static float input_x_0(LinkedList<Float> f_of_x, float x_0)
	{
		float sum = 0;
		int size = f_of_x.size();
		//System.out.println("Entering INPUT_X_0");
		
		for(int i = 0; i < size; i++)
		{
			sum += Math.pow(x_0, size-(i+1)) * f_of_x.get(i);
			//System.out.println("SUM = " + sum + " += " + x_0 + "^" + (size-(i+1)) + " * " + f_of_x.get(i));
		}
		
		return sum;
	}

}
