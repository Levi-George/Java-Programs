/*Program Name: fourteenThree
 * Author: Levi George
 * Date Last Updated: 7/9/2019
 * Program Purpose: To display three different card images using javafx
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.Random;
public class fourteenThree extends Application 
{

	public static void main(String[] args) 
	{
		Application.launch(args);
		
	}
	public void start(Stage primaryStage)
	{
		//this blob of code helps generate random numbers for the cards
		Random dnar = new Random();
		int c1 = dnar.nextInt(52) + 1;//holds int value for card 1
		int c2 = dnar.nextInt(52) + 1;//holds int value for card 2
		
		//This loop makes sure that card 2 is not the same as card 1
		while(c2 == c1)
		{
			c2 = dnar.nextInt(52) + 1;
		}
		int c3 = dnar.nextInt(52) + 1;//holds int value for card 3
		
		//This loop makes sure card 3 is not the same as card 1 and card 2
		while(c3 == c2 || c3 == c1)
		{
			c3 = dnar.nextInt(52) + 1;
		}
		
		//These two strings will be used to develop a card address in addition the the card numbers stored in c1, c2, and c3
		String first = "card/";
		String sec = ".png";
		
		//sets primary stage title
		primaryStage.setTitle("Your Cards");
		
		
		//first + Integer.toString(c3) + sec
		//gets the card images for each image object
		Image card1 = new Image(first + Integer.toString(c1) + sec);
		Image card2 = new Image(first + Integer.toString(c2) + sec);
		Image card3 = new Image(first + Integer.toString(c3) + sec);
		
		//declares image view objects with respective card images
		ImageView iVcard1 = new ImageView(card1);
		ImageView iVcard2 = new ImageView(card2);
		ImageView iVcard3 = new ImageView(card3);
		
		//declares a new grid pane
		GridPane grid1 = new GridPane();
		
		//adds image view objects to grid pane
		grid1.add(iVcard1, 0, 0);
		grid1.add(iVcard2, 1, 0);
		grid1.add(iVcard3, 2, 0);
		
		//scene is declared and has grid object added
		Scene scene = new Scene(grid1, 216, 96);
		
		//adds scene to stage and displays it
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
