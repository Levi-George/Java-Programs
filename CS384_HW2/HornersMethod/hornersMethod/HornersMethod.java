package hornersMethod;
import java.io.*;
import java.util.LinkedList;
import java.lang.String;

public class HornersMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<Integer> coefficients = new LinkedList<Integer>();
		
		System.out.println(args);

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
		
		int x_0 = Integer.parseInt(args[args.length - 1]); //get last string and convert it to int 
														
		int i = 0;
		
		
		//parse out our values into the DS
		
		//linked list - based on each X, it will have our node address, coefficient and power
		
		//P(x) = (x-x_0)Q(x) + b0
		//Q(x) = P'(x)
		//b_k = a_k + b_(k+1)*x_0
		
		//store each value in a position based on its power, with x_0 being excluded
		//we got it above
		while(i+1 != args.length)
		{
			int lastOrder = 0;
			coefficients.addLast(Integer.parseInt(args[i]));
			
			//if there is a gap that is greater than 1 space between orders then
			//we want to add a zero into the coefficient list (makes it easier on me to calculate)
			if(lastOrder > (Integer.parseInt(args[i+1])+1) )
			{
				coefficients.addLast(0);
			}
			
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
			System.out.println("" + Coeff_List.get(i) + "x^" + i);
		}
		
		
	}

}
