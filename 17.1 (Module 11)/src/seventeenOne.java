/* Program Name: seventeenOne
 * Author: Levi George
 * Date Last Updated: 7/21/2019
 * Program Purpose: To create a new or use an existing document and write 100 random integers to that document
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
public class seventeenOne 
{

	public static void main(String[] args)
	{
		
		try {
			//printwriter object used to write to an external file
			PrintWriter typist = new PrintWriter("Exercise17_01.txt");
			
			//this for loop will write 100 integers to the typist object's target file
			for(int i = 0; i < 101; i++)
			{
				Random dnar = new Random();
				
				int n = dnar.nextInt(101);
				
				typist.print(n);
				typist.print(" ");
				
				
			}
			
			//closes typist to prevent any data damage
			typist.close();
		//catches exceptions
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
