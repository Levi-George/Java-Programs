/* Program Name: seventeenElven
 * Author: Levi George
 * Date Last Updated: 7/21/2019
 * Program Purpose: This program will take a file that the user enters and split it into a desired number of documents
 */

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
public class seventeenEleven extends Application
{
	//declares labels for program's gui
	private Label lab1 = new Label("File Name: ");
	private Label lab2 = new Label("Number of Files: ");
	
	//declares a button for gui
	private Button button = new Button("Split file!");
	
	//declares textFields for the gui
	private TextField fName = new TextField();
	private TextField fNum = new TextField();
	private TextField test = new TextField();
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	public void start(Stage primary)
	{
		GridPane pane = new GridPane();
		
		//Adds all items for gui into Grid Pane
		pane.add(lab1, 0, 1);
		pane.add(lab2, 0, 2);
		pane.add(fName, 1, 1);
		pane.add(fNum, 1, 2);
		pane.add(test, 1, 3);
		pane.add(button, 0, 4);
		
		//adds event handling to button
		button.setOnAction(new SplitHandler());
		
		//adds pane to scene and sets dimensions
		Scene scene = new Scene(pane, 300, 150);
		
		//adds scene to primary Stage and names it 
		primary.setScene(scene);
		primary.setTitle("File Splitter");
		primary.show();
	}
	
	class SplitHandler implements EventHandler<ActionEvent>
	{

		public void handle(ActionEvent e) 
		{
			String fileName = fName.getText();
			int num = Integer.parseInt(fNum.getText());
			
			RandomAccessFile scan = null;
			PrintWriter typist = null;
			
			try {
				scan = new RandomAccessFile(new File(fileName), "rw");
				long rem = 0, bytes = scan.length();
				
				//test.setText("" + totalChars);
				scan.seek(0);
				for(int i = 0; i < num; i++)//TODO something about bytes and ensuring all bytes are copied
				{
					String newFile = fileName + "." + i;
					typist = new PrintWriter(new File(newFile));
					if(i == num)
					{
						rem = bytes%num;
					}
					for(int j = 0; j < ((bytes/num) + rem); j++)
					{
						typist.write(scan.readByte());
					}
					
					typist.close();
				}
				
				scan.close();
			} 
			catch (IOException e2) {
				System.out.println("File splitting has completed");
				test.setText("File has finished splitting");
				typist.close();
				
			}
			
			
		}
		
	}

}
