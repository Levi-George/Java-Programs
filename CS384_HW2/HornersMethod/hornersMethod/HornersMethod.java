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
		
		
		printCoeff_LL(coefficients);
		
	}
	
	static void printCoeff_LL(LinkedList<Integer> Coeff_List)
	{
		
		for(int i = 0; i < Coeff_List.size(); i++)
		{
			System.out.println("" + Coeff_List.get(i) + "x^" + (Coeff_List.size() - i));
		}
		
		
	}

}
