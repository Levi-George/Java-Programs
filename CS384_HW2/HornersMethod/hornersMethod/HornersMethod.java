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

public class HornersMethod {

	public static void main(String[] args) {
	
		//linked list - based on each X, it will have our node address, coefficient and power
		LinkedList<Float> coefficients = new LinkedList<Float>();
		
		inputValidation(args);//validate user input
		
		float x_0 = Float.parseFloat(args[args.length - 1]); //get last string and convert it to int 
		
		int lastOrder = 0;
		int i = 0;
		
		//parse out our values into the DS
		while(i+1 != args.length)
		{
			
			float coeffToAdd = Float.parseFloat(args[i]);
			
			
			//if there is a gap that is greater than 1 space between orders then
			//we want to add a zero into the coefficient list (makes it easier on me to calculate)
			if((lastOrder - (Float.parseFloat(args[i+1]))) > 1 )
			{
				
				coefficients.addLast((float)0.0);
			}
			
			coefficients.addLast(coeffToAdd);
			
			//set last order as the current order
			lastOrder = Integer.parseInt(args[i+1]); //get the power of the coefficient
			
			//move ahead in the args array
			i += 2;
		}
		
		//get our true value
		float f_x_0 = calculate_x_0(coefficients, x_0);
		
		//Horner's Calculations
		//
		//calculate Q_of_x
		LinkedList<Float> Q_of_x = calculate_QofX(P_of_x, x_0);
		float p_x_0 = Q_of_x.remove(Q_of_x.size()); //get remainder from last position
		
		
		
		//DEBUG CODE
		//printCoeff_LL(coefficients);
		//System.out.println(x_0);
		
	}
	
	static void printCoeff_LL(LinkedList<Float> Coeff_List)
	{
		
		for(int i = 0; i < Coeff_List.size(); i++)
		{
			System.out.println("" + Coeff_List.get(i) + "x^" + (Coeff_List.size() - i));
		}
		
		
	}
	
	static void calculate_QofX(LinkedList<Float> Coeff_List, float x_0)
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
	
	static LinkedList<Float> calculate_QofX(LinkedList<Float> f_of_x, float x_0)
	{
		LinkedList<Float> Q_of_x = new LinkedList<Float>();
		
		//b_k = a_k + b_k+1 * x_0
		float b_k =0;
		float b_k1 = 0;
		
		for(int i = 0; i < f_of_x.size(); i++)
		{
			b_k = f_of_x.get(i) + (b_k1 * x_0);
			
			
			Q_of_x.addLast(b_k);
		}
		
		
		return Q_of_x;
	}
	
	static float calculate_x_0(LinkedList<Float> Coeff_List, float x_0)
	{
		float sum = 0;
		int size = Coeff_List.size();
		for(int i = 0; i < size; i++)
		{
			sum += Math.pow(x_0, size) * Coeff_List.get(i);
		}
		
		return sum;
	}
	
	static void HornersMethod(LinkedList<Float> Coeff_List, float x_0)
	{
		int n = Coeff_List.size();
		float y = 0;//P(x_0)
		float z = 0;//P'(x_0)
		
		y = 0; //a_n
		z = 0; //a_n
		
		for(int j = 0; j < n; j++)
		{
			y = 0; //x_0 * y + a_j
			z = 0; //x_0 * z + y
		}
		
		y = 0; //x_0 * y + a_0
	}

}
