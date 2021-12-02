package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import application.model.Game;
import application.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

public class DiceController {
	private Spin clock;
	private ArrayList<Person> personList, playerList;
	Game dice;
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	GridPane grid1 = new GridPane();
	
	@FXML
	GridPane grid2 = new GridPane();
	
	@FXML
	ImageView dieImage;
	
	@FXML
	Button rollButton;
	
	@FXML
	Button holdButton;
	
	@FXML
	TextField player1turn;
	
	@FXML
	TextField player2turn;
	
	@FXML
	TextField player1total;
	
	@FXML
	TextField player2total;
	
	@FXML
	VBox player1box;
	
	@FXML
	VBox player2box;
	
    @FXML
    private Label player1Text;
    
    @FXML
    private Label player2Text;
	
	@FXML
	Label title;
	//Start spinning animation
	@FXML
	void spinAnim(MouseEvent event)
	{
		clock.start();
	}

	
	
//Setting the spin, max spin it does per roll, and images die should be set to
	private class Spin extends AnimationTimer{

		private long FRAME_PER_SEC = 30L;
		private long INTERVAL = 1000000000L / FRAME_PER_SEC;
		private int MAX_SPIN = 15;
		private long last = 0;
		int count = 0;
		int sides = 6;
		
		@Override
		public void handle(long n) {
			if(n - last > INTERVAL) {
				
				int r = 2 + (int)(Math.random() * 5);
				try {
					
					setdieImage(r);
				} catch (Exception e) {
					e.printStackTrace();
				}
				last = n;
				count++;
				if(count > MAX_SPIN) {
					clock.stop();
					roll();
					count = 0;
				}
			}
			
		}
		
	}
	
	public void startRound(Person person1, Person person2) {
		
		clock = new Spin();
		//dice = new Game("Player 1", "Player 2");
		dice = new Game(person1.getName(), person2.getName());
		player1Text.setText(person1.getName());
		player2Text.setText(person2.getName());
		
		try {
			setdieImage(dice.getDie().getTop());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rollButton.setOnAction(event -> roll());
		holdButton.setOnAction(event -> hold());
		
		update();
	}

	//Updating the text field with die rolled and which player is on current roll
	public void update() {
		
		player1turn.setText("" + dice.getPlayer1().getTurnScore());
		player1total.setText("" + dice.getPlayer1().getTotalScore());
		player2turn.setText("" + dice.getPlayer2().getTurnScore());
		player2total.setText("" + dice.getPlayer2().getTotalScore());
		
		if(dice.player1Turn()) {
		 ((Region) player1box).setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		 ((Region) player2box).setBackground(null);
		 
		}
		else {
		((Region) player2box).setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	    ((Region) player1box).setBackground(null);
		}
		
		if(dice.GameOver()) {
			
			title.setText("Game Over! " + dice.getCurrent().getName()+ " is the Winner!");
			//startRound();
		}
		
	}
//Setting the die image
	public void setdieImage(int top) throws Exception {

		try {
		dieImage.setImage(new Image(new File("Images/Dice" + top + ".png").toURI().toURL().toString()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	//Calling on action to set roll and update
	private void roll() {
		
		dice.roll();
		update();
	}
	//calling on hold to save dice number
	private void hold() {
		dice.hold();
		update();
	}
	
	public void Menu(ActionEvent event) throws IOException{
		
			// Fix
		  URL url = new File("src/application/view/Menu.fxml").toURI().toURL();
          AnchorPane root = (AnchorPane)FXMLLoader.load(url);
          Scene scene = new Scene(root,800,800);
          Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	  stage.setScene(scene);
      	  stage.show();

	}
	
	public void initializeData(ArrayList<Person> personList) {
    	
    	this.playerList = new ArrayList<Person>();
    	
    	this.personList = personList;
    	
    	System.out.println(personList.size());
    	
    	for(Person person: personList)
    		playerList.add(person);
    	
    	startRound(playerList.get(0), playerList.get(1));
    }

}
