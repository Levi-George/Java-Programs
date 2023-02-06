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
		
		inputValidation(args);//validate user input
		
		//linked list - based on each X, it will have our node address, coefficient and power
		LinkedList<Float> P_of_x = new LinkedList<Float>();
		float x_0 = Float.parseFloat(args[args.length - 1]); //get last string and convert it to int 
		int lastOrder = 0;
		int i = 0;
		
		//TODO add code for larger gaps between each term
		//parse out our values into the DS
		while(i+1 != args.length)//check next space of nothing, so we don't OOB
		{
			//get the coefficient
			float coeffToAdd = Float.parseFloat(args[i]);
			
			//add zero to coeff list when a gap between powers exists (i.e. x^4 + x^2)
			if((lastOrder - (Float.parseFloat(args[i+1]))) > 1 )
			{
				
				P_of_x.addLast((float)0.0);
			}
			
			//add the actual coeff
			P_of_x.addLast(coeffToAdd);
			
			//set last order as the current order
			lastOrder = Integer.parseInt(args[i+1]); //get the power of the coefficient
			
			//move ahead in the args array
			i += 2;
		}
		
		float p_x_0_true = input_x_0(P_of_x, x_0);
		
		System.out.print("P of X from calculations: " + p_x_0_true);
		
		//calculate Q_of_x
		LinkedList<Float> Q_of_x = calculate_QofX(P_of_x, x_0);
		float p_x_0 = Q_of_x.remove(Q_of_x.size()); //get remainder from last position
		
		System.out.print("\t" + "P of X from Horner's Method: " + p_x_0);
		
		
		float q_of_x0 = input_x_0(Q_of_x, x_0);
		float error = 10;
		float x_next = x_0 - (p_x_0_true/q_of_x0);
		
		error = p_x_0_true - x_next;
		
		while(error > Math.pow(10, -4))
		{
			float P_x_n = input_x_0(P_of_x, x_next);
			float Q_x_n = input_x_0(Q_of_x, x_next);
			x_next = x_next - P_x_n/Q_x_n;
			
			error = p_x_0_true - x_next;
		}
		
	}
	
	//***************	END OF MAIN - START OF FUNCS
	
	static void printCoeff_LL(LinkedList<Float> f_of_x)
	{
		
		for(int i = 0; i < f_of_x.size(); i++)
		{
			System.out.println("" + f_of_x.get(i) + "x^" + (f_of_x.size() - i));
		}
		
		
	}
	
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
	
	static float input_x_0(LinkedList<Float> f_of_x, float x_0)
	{
		float sum = 0;
		int size = f_of_x.size();
		for(int i = 0; i < size; i++)
		{
			sum += Math.pow(x_0, size) * f_of_x.get(i);
		}
		
		return sum;
	}
	
	static void TrueHorners(LinkedList<Float> F_of_x, LinkedList<Float> f_of_x, float x_0)
	{
		float y = F_of_x.get(F_of_x.size()-1);
		float z = f_of_x.get(f_of_x.size()-1);
		
		for(int i = F_of_x.size()-2; i > 0; i-- )
		{
			y = x_0 * y + F_of_x.get(i);
			z = x_0 * z + y;
			
		}
		
		y = x_0 * y + F_of_x.get(0);
		
		System.out.println("y = " + y + "; z = " + z);
	}

}
