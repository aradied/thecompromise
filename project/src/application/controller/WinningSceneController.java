package application.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import application.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class WinningSceneController {
	
	@FXML
	private AnchorPane mainpane;
	
	@FXML
	private Label WinningName;
	
	@FXML
	Button displayWinner;
	
	@FXML
	private ImageView image1;
	
	@FXML
	private ImageView image2;
	
	@FXML
	private ImageView image3;
	
	@FXML
	private Button HomeButton;
	
	
	//handles click on home button and goes back to main screen
	@FXML
	void HandleHomeButton(ActionEvent event) throws IOException {
		
		Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	currWindow.close();
		
	}
	
	public void initializeData(Person winner) throws IOException {
    	
    	InputStream stream1 = new FileInputStream("Images/groupprjwinimg.jpg");
		InputStream stream2 = new FileInputStream("Images/looneytunes_gif.gif");
		InputStream stream3 = new FileInputStream("Images/giffy.gif");
		Image image1 = new Image(stream1);
		Image image2 = new Image(stream2);
		Image image3 = new Image(stream3);
		this.image1.setImage(image1);
		this.image2.setImage(image2);
		this.image3.setImage(image3);
		
		WinningName.setText("The Winner is  " + winner.getName() +" and the location they have choosen to go to is " + winner.getPlace());
    }
	
}
