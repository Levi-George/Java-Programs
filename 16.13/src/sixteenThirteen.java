import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

public class sixteenThirteen extends Application{
	
	//Text fields for scene
	private TextField balField = new TextField();
	private TextField timeField = new TextField();
	private TextField interestRateField = new TextField();
	private TextField newBalfield = new TextField();
	
	//button for scene
	private Button Calculate = new Button("Calculate!");
	
	//Text Area
	private TextArea output = new TextArea();
	
	//Array list of Loans
	private ArrayList<Loan> Loans = new ArrayList();
	
	//string which will be used for displaying loan information
	private String outString = "";
	
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
		
		//Adds the previously mentioned text fields to the grid
		calc.add(balField, 1, 0);
		calc.add(timeField, 1, 1);
		
		//adds a button to the grid
		calc.add(Calculate, 0, 2);
		
		//adds a text area
		calc.add(output, 0, 4);
		
		//Calculate.setOnAction(new CalculateHandler());
		
		//Arranges event handling for two fields and a button
		balField.setOnAction(new EnterHandlerTF1());
		timeField.setOnAction(new EnterHandlerTF2());
		interestRateField.setOnAction(new EnterHandlerTF3());
		Calculate.setOnAction(new CalculateHandler());
		
		//Creates a scene object
		Scene scene = new Scene(calc, 225, 150);
		
		//Sets the title of, adds the scene and displays the GUI
		primaryStage.setTitle("Eat your heart out FV()");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//start function end
	
	//handles the pressing of the calculate button
	class CalculateHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			double rate = .05;
			//creates objects for each different loan rate
			for(int i = 0; i < 25; i++)
			{
				Loans.add(new Loan(rate, Integer.parseInt(timeField.getText()), Double.parseDouble(balField.getText())));
				rate += (1.0/8)/100;
			}
			
			//stores information into a string regarding every loan rate
			for(int i = 0; i < 25; i++)
			{
				outString += "\nInterest Rate: " + Loans.get(i).getAnnualInterestRate()*100 + "%";
				outString += "\nMonthly Interest: $" + Loans.get(i).getMonthlyPayment();
			}
			
			//outputs the information
			output.setText(outString);
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

}//Class sixteenThirteen End
