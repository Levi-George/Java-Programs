/* Program Name: nineteenNine
 * Author: Levi George
 * Date Last Updated: 7/27/2019
 * Program Purpose: To fully sort two arrays of different types using a generic method
 */
import java.util.ArrayList;
import java.util.Random;
public class nineteenNine {

	public static void main(String[] args) 
	{
		//Two array lists, one for integers, one for doubles
		ArrayList<Integer> intList = new ArrayList<Integer>();
		ArrayList<Double> dubList = new ArrayList<Double>();
		
		//Random number generator
		Random dnar = new Random();
		
		//populates the two arrays with numbers
		for(int i = 0; i < 20; i++)
		{
			intList.add(dnar.nextInt());
			dubList.add(dnar.nextDouble());
		}
		
		//Calls the sort function on the two array lists
		sort(intList);
		sort(dubList);
		
		/*Prints the contents of the two array lists as a sanity check
		for(int i = 0; i < 20; i++)
		{
			System.out.print(intList.get(i) + " ");
		}
		System.out.print("\n");
		for(int i = 0; i < 20; i++)
		{
			System.out.print(dubList.get(i) + " ");
		}
		*/
	}
	//a generic sort method used to sort an array list of any type
	public static <E extends Comparable<E>> ArrayList<E> sort(ArrayList<E> numlist)
	{
		//gets list size
		int len = numlist.size();
				
		//sorts the Array list using selection sort
		for(int i = 0; i < (len-1); i++)
		{
			
			int minIndex = i;//minIndex holds the current value of the i variable, will hold the lowest value index
			for(int j = i+1; j < len; j++)
			{
				//runs if the value at minIndex in the array is less than the value at j
				if(numlist.get(j).compareTo(numlist.get(minIndex)) < 0)
				{
					minIndex = j;//switches minIndex to the new lowest value index
				}
			}
			
			//switches values places, so that the lowest value index is switched with the value at i
			E temp = numlist.get(minIndex);
			numlist.set(minIndex, numlist.get(i));
			numlist.set(i, temp);
		}
		
		
		return numlist;
	}

}
