/* Program Name: twentyThree
 * Author: Levi George
 * Date Last Updated: 7/28/2019
 * Program Purpose: To quiz a user on the states and their capitals in random order
 */
import java.util.Collections;
import java.util.Random;
import java.util.*;
public class twentyThree {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//creates random number object, scanner object, declares vector for the states.
		Random dnar = new Random();
		Scanner scan = new Scanner(System.in);
		Vector<String> States = new Vector<>();
		//A-states
		States.add("Alaska"); States.add("Alabama"); States.add("Arizona"); States.add("Arkansas");
		//C-states
		States.add("California"); States.add("Colorado"); States.add("Connecticut");
		//D-states through H
		States.add("Delaware");
		States.add("Florida");
		States.add("Georgia");
		States.add("Hawaii");
		//I-states
		States.add("Idaho"); States.add("Illinois"); States.add("Indiana"); States.add("Iowa");
		//K through L-states
		States.add("Kansas"); States.add("Kentucky");
		States.add("Louisiana");
		
		//M-states
		States.add("Maine"); States.add("Maryland");
		States.add("Massachusetts"); States.add("Michigan");	States.add("Minnesota"); States.add("Mississippi"); States.add("Missouri");	States.add("Montana");
		//N and O-states
		States.add("Nebraska");	States.add("Nevada"); States.add("New Hampshire"); States.add("New Jersey");
		States.add("New York"); States.add("North Carolina"); States.add("North Dakota");
		States.add("Ohio");	States.add("Oklahoma"); States.add("Oregon");
		
		//P through W-states
		States.add("Pennsylvania");
		States.add("Rhode Island");
		States.add("South Carolina"); States.add("South Dakota");
		States.add("Tennessee"); States.add("Texas");
		States.add("Utah");
		States.add("Vermont"); States.add("Virginia");
		States.add("Washington"); States.add("West Virginia"); States.add("Wisconsin"); States.add("Wyoming");
		
		//Creates a map and adds state key values with corresponding capital values
		Map<String, String> quiz = new HashMap<>();
		quiz.put("Alaska", "Juneau");
		quiz.put("Alabama", "Montgomery");
		quiz.put("Arizona", "Phoenix");
		quiz.put("Arkansas","Little Rock");
		quiz.put("California","Sacramento");
		quiz.put("Colorado","Denver");
		quiz.put("Connecticut","Hartford");
		quiz.put("Delaware","Dover");
		quiz.put("Florida","Tallahassee");
		quiz.put("Georgia","Atlanta");
		quiz.put("Hawaii","Honolulu");
		quiz.put("Idaho","Boise");
		quiz.put("Illinois","Springfield");
		quiz.put("Indiana","Indianapolis");
		quiz.put("Iowa","Des Moines");
		quiz.put("Kansas", "Topeka");
		quiz.put("Kentucky","Frankfort");
		quiz.put("Louisiana","Baton Rouge");
		quiz.put("Maine","Augusta");
		quiz.put("Maryland","Annapolis");
		quiz.put("Massachusetts","Boston");
		quiz.put("Michigan","Lansing");
		quiz.put("Minnesota","St. Paul");
		quiz.put("Mississippi","Jackson");
		quiz.put("Missouri","Jefferson City");
		quiz.put("Montana","Helena");
		quiz.put("Nebraska","Lincoln");
		quiz.put("Nevada","Carson City");
		quiz.put("New Hampshire","Concord");
		quiz.put("New Jersey","Trenton");
		quiz.put("New Mexico","Santa Fe");
		quiz.put("New York","Albany");
		quiz.put("North Carolina","Raleigh");
		quiz.put("North Dakota","Bismarck");
		quiz.put("Ohio","Columbus");
		quiz.put("Oklahoma","Oklahoma City");
		quiz.put("Oregon","Salem");
		quiz.put("Pennsylvania","Harrisburg");
		quiz.put("Rhode Island","Providence");
		quiz.put("South Carolina","Columbia");
		quiz.put("South Dakota","Pierre");
		quiz.put("Tennessee","Nashville");
		quiz.put("Texas","Austin");
		quiz.put("Utah","Salt Lake City");
		quiz.put("Vermont","Montpelier");
		quiz.put("Virginia","Richmond");
		quiz.put("Washington","Olympia");
		quiz.put("West Virginia","Charleston");
		quiz.put("Wisconsin","Madison");
		quiz.put("Wyoming","Cheyenne");
		
		//this string will hold the user response
		String ans = "";
		
		//This vector will hold the indices of the states that have already been used
		Vector<Integer> completed = new Vector<>();
		
		//this will track the user's score
		int score = 0;
		
		//This will loop through every question on the quiz
		for(int i = 0; i < 50; i++)
		{
			//generates a new random number
			int random = dnar.nextInt(49);
			
			//This will run as long as the user has completed one quesitons
			if(completed.size() > 0)
			{
				//this for-loop will run through all the completed states indices
				for(int j = 0; j < completed.size(); j++)
				{
					//this will check to see if the new random number that has been generated is a number that has already been used
					if(random == completed.get(j))
					{
						//generates a new random number and starts checking all of the values again
						random = dnar.nextInt(49);
						j = 0;
					}
				}
			}
			
			
			//prompts and accepts user input
			System.out.println((i+1) + ". What is the capital of " + States.get(random));
			ans = scan.nextLine();
			
			//checks to see if the user has the correct answer
			if(ans.equalsIgnoreCase(quiz.get(States.get(random))))
			{
				System.out.println("That is correct!");
				score++;
			}
			else
			{
				System.out.println("The answer should be " + quiz.get(States.get(random)));
			}
			
			//adds the question that was completed to the completed vector, so that they will not be done twice
			completed.add(random);
		}
		
		//displays score
		System.out.println("You got " + score + " out of 50!");
		
	}

}
