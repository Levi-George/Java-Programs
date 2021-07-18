/*Program Name: fifteenFive
 * Author: Levi George
 * Date last Updated: 7/10/2019
 * Program Purpose: To create a simple calculator to calculate interest of an investment
 */
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class fifteenSeven extends Application
{
	//Circle Object to be used in display of circle
	private Circle cirquedesoleil = new Circle();
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	public void start(Stage primaryStage)
	{
		//Pane used to display the the circle created earlier
		Pane pain = new Pane();
		
		//sets the coordinates and attributes of the circle
		cirquedesoleil.setCenterX(250);
		cirquedesoleil.setCenterY(250);
		cirquedesoleil.setRadius(25.0);
		cirquedesoleil.setFill(Color.TEAL);
		
		//Alternate code used for testing
		//pain.setOnMousePressed(new PressHandler());
		//pain.setOnMouseReleased(new ReleaseHandler());
		
		//Used to change the color of the circle
		cirquedesoleil.setOnMousePressed(new PressHandler());
		cirquedesoleil.setOnMouseReleased(new ReleaseHandler());
		
		//adds circle to the pane
		pain.getChildren().add(cirquedesoleil);
		
		//adds the pane to the scene and then adds the scene to the primary stage, sets the title of the page and displays the stage
		Scene scene = new Scene(pain, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Circle Magic");
		primaryStage.show();
	}
	//Handles mouse pressed events, changes color of circle to black
	class PressHandler implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			cirquedesoleil.setFill(Color.BLACK);
		}
	}
	//Handles mouse released events, changes color of circle to white
	class ReleaseHandler implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			cirquedesoleil.setFill(Color.WHITE);
		}
	}
}
