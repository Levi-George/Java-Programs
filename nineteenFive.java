/*Program Name: nineteenFive
 * Author: Levi George
 * Date Last Updated: 7/27/2019
 * Program Purpose: To find the highest valued item in three different arrays using the same function
 */
import java.util.ArrayList;
import java.util.Random;
public class nineteenFive {

	public static void main(String[] args) 
	{
		// Array Lists that store integers, doubles, and characters
		ArrayList<Integer> integers = new ArrayList<Integer>();
		ArrayList<Double> doubles = new ArrayList<Double>();
		ArrayList<Character> Chars = new ArrayList<Character>();
		
		//Arrays that store integers, doubles, and characters
		Integer[] integerArray = new Integer[20];
		Double[] doubleArray = new Double[20];
		Character[] charArray = new Character[20];
		
		//Random number generator
		Random dnar = new Random();
		
		//populates arrays and arrayLists with values
		for(int i = 0; i < 20; i++)
		{
			integers.add(dnar.nextInt());
			doubles.add(dnar.nextDouble());
			Chars.add((char)(dnar.nextInt(256)));
			
			integerArray[i] = dnar.nextInt();
			doubleArray[i] = dnar.nextDouble();
			charArray[i] = (char)(dnar.nextInt(256));
		}
		
		//prints values of the arrays as a sanity check
		/*for(int i = 0; i < 20; i++)
		{
			System.out.print(integerArray[i] + " ");
		}
		System.out.print("\n");
		for(int i = 0; i < 20; i++)
		{
			System.out.print(doubleArray[i] + " ");
		}
		System.out.print("\n");
		for(int i = 0; i < 20; i++)
		{
			System.out.print(charArray[i] + " ");
		}
		System.out.print("\n");
		*/
		
		//Prints out the max values of each array list
		System.out.println(maxList(integers)+ "");
		System.out.println(maxList(doubles)+"");
		System.out.println(maxList(Chars)+ "");
		
		//Prints out the max values of each array
		System.out.println(maxArray(integerArray)+"");
		System.out.println(maxArray(doubleArray)+"");
		System.out.println(maxArray(charArray)+"");

	}
	
	//generic function used for finding the maximum value in any type of array list
	public static <E extends Comparable<E>> E maxList(ArrayList<E> list)
	{
		E max = list.get(0);
		
		//compares the current maximum to the next item in the list 
		for(int i = 0; i < list.size();i++)
		{
			//sets the current max to the current item in the list if the current item is greater
			if(max.compareTo(list.get(i)) < 0)
			{
				max = list.get(i);
			}
			
			
		}
		
		return max;
	}
	//generic function used for finding the maximum value in any type of array list
	public static <E extends Comparable<E>> E maxArray(E[] list)
	{
		//sets the highest value in the list to the first item
		E max = list[0];
		
		//checks to see if the other items in the array are greater than the max
		for(int i = 0; i < list.length;i++)
		{
			//sets the max to the current item if it is greater than the original max
			if(max.compareTo(list[i]) < 0)
			{
				max = list[i];
			}
			
			
		}
		
		return max;
	}

}
