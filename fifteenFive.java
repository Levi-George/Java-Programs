/*Program Name: fifteenFive
 * Author: Levi George
 * Date last Updated: 7/10/2019
 * Program Purpose: To create a simple calculator to calculate interest of an investment
 */
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class fifteenFive extends Application
{
	//Text fields for scene
	private TextField balField = new TextField();
	private TextField timeField = new TextField();
	private TextField interestRateField = new TextField();
	private TextField newBalfield = new TextField();
	
	//button for scene
	private Button Calculate = new Button("Calculate!");
	
	public static void main(String[] args) 
	{
		Application.launch(args);

	}
	
	public void start(Stage primaryStage)
	{
		//declares gridpane for organizing labels and textfields
		GridPane calc = new GridPane();
		
		//declares labels
		Label balLabel = new Label("Balance:");
		Label timeLabel = new Label("Years:");
		Label interestRateLabel = new Label("Interest Rate:");
		Label newBalance = new Label("New Balance:");
		
		//stops user from changing the newBalfield
		newBalfield.setEditable(false);
		
		//adds the labels to the grid
		calc.add(balLabel, 0, 0);
		calc.add(timeLabel, 0, 1);
		calc.add(interestRateLabel, 0, 2);
		calc.add(newBalance, 0, 3);
		
		//Adds the previously mentioned text fields to the grid
		calc.add(balField, 1, 0);
		calc.add(timeField, 1, 1);
		calc.add(interestRateField, 1, 2);
		calc.add(newBalfield, 1, 3);
		
		//adds a button to the grid
		calc.add(Calculate, 1, 4);
		
		//Calculate.setOnAction(new CalculateHandler());
		
		//Arranges event handling for two fields and a button
		balField.setOnAction(new EnterHandlerTF1());
		timeField.setOnAction(new EnterHandlerTF2());
		interestRateField.setOnAction(new EnterHandlerTF3());
		Calculate.setOnAction(e -> FutureValue());
		
		//Creates a scene object
		Scene scene = new Scene(calc, 225, 150);
		
		//Sets the title of, adds the scene and displays the GUI
		primaryStage.setTitle("Eat your heart out FV()");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//start function end
	
	//Handles pressing of the calculate button
	class CalculateHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			FutureValue();
		}
	}//CalculateHandler end
	
	//handles pressing enter on an object, used specifically with the balField
	class EnterHandlerTF1 implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			timeField.requestFocus();
		}
	}
	
	//Handles pressing enter on an text field, specifically timeField
	class EnterHandlerTF2 implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			interestRateField.requestFocus();
		}
	}
	//Handles pressing enter on a text field, specifically the interestRateField
	class EnterHandlerTF3 implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			Calculate.requestFocus();
		}
	}
	
	//Calculates the future value of an investment
	public void FutureValue()
	{
		//gets values from the text fields then calculates the new balance and places it inside the newBalfield
		double interest = Double.parseDouble(interestRateField.getText());
		double bal = Double.parseDouble(balField.getText());
		double time = Double.parseDouble(timeField.getText());
		interest = interest/100;
		double newBal = bal * Math.pow((1+interest), time);
		
		String newBalStr = Double.toString(newBal);
		
		newBalfield.setText(newBalStr);
	}
	
}//fifteenFive class end


