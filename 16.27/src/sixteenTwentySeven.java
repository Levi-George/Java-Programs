/*Program Name: sixteenTwentySeven
 * Author: Levi George
 * Date Last Updated: 7/14/2019
 * Program Purpose: To perform a count down to playing one of the best songs of all time
 */
import java.io.File;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.util.concurrent.TimeUnit;

public class sixteenTwentySeven extends Application{
	
	//Text objects for the program
	private TextField timeEntry = new TextField();
	private Label tBox2 = new Label();
	
	private Label tLabel = new Label("Timer Length: ");
	
	//button to commence countdown
	private Button commence = new Button("Commence");
	
	//song's path
	String song = "bin\\Song\\America - A Horse With No Name.mp3";
	
	//formats string to proper format, declares media, media player, and mediaviewer objects
	private Media media = new Media(new File(song).toURI().toString());
	private MediaPlayer player = new MediaPlayer(media);
	private MediaView viewer = new MediaView(player);
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Application.launch(args);
		
	}//main end
	
	public void start(Stage primary) throws Exception 
	{	//declares grid pane object
		GridPane pane = new GridPane();
		
		//sets a event handler to the commence button
		commence.setOnAction(new TimerHandler());
		
		//Adds text field box, and buttons to pane
		pane.add(tLabel, 0, 0);
		pane.add(timeEntry, 1, 0);
		pane.add(commence, 1, 1);
		pane.add(tBox2, 1, 2);
		
		//sets player volume
		player.setVolume(1.0);
		
		//adds pane to a new scene, sets stage title, adds scene to title, displays stage
		Scene scene = new Scene(pane, 200, 200);
		primary.setTitle("Horse With No Name");
		primary.setScene(scene);
		primary.show();
	}//start end
	
	//handler for commence button
	class TimerHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			//Gets the length of time for the time
			int len = Integer.parseInt(timeEntry.getText());

			//calculates and displays remaining time, then pauses program
			for(int i = 0; i < len; i++)
			{
				String currTime = Integer.toString(len - i);
				System.out.println("Time Remaining: " + currTime);
				//tBox2.setText("Time Remaining: " + currTime);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//plays one of the best songs ever
			player.play();
				
				
		}
	}//class TimeHandler end
	
	
}//class sixteenTwentySeven end
