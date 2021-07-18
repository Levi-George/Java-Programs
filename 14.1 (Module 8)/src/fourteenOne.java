/*Program Name: fourteenOne
 * Author: Levi George
 * Date Last Updated: 7/9/2019
 * Program Purpose: To display four images using javafx
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class fourteenOne extends Application 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception
	{
		//Sets the title of the primary stage
		primaryStage.setTitle("First Stage");
		
		//declares and accesses the numerous images
		Image us = new Image("image/us.gif");
		Image can = new Image("image/ca.gif");
		Image chi = new Image("image/china.gif");
		Image uk = new Image("image/uk.gif");
		
		//places images into imageView items
		ImageView usImage = new ImageView(us);
		ImageView canImage = new ImageView(can);
		ImageView chiImage = new ImageView(chi);
		ImageView ukImage = new ImageView(uk);
		
		//declares grid pane object
		GridPane grid1 = new GridPane();
		
		//adds image view items into grid1
		grid1.add(usImage, 0, 0);
		grid1.add(canImage, 0, 1);
		grid1.add(chiImage, 1, 0);
		grid1.add(ukImage, 1, 1);
		
		//sets grid size and adds grid1 grid pane
		Scene scene = new Scene(grid1, 350, 200);
		
		//adds scene to primary stage and displays
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
