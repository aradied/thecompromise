package application.controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import application.model.Game;
import application.model.Person;
import application.model.Rounds;
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
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class DiceController {
	private Spin clock;
	Game dice;
	Rounds rounds;
	
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
					try {
						roll();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count = 0;
				}
			}
			
		}
		
	}
	
	public void startRound(String person1, String person2) throws Throwable {
		
		clock = new Spin();
		//dice = new Game("Player 1", "Player 2");
		dice = new Game(person1, person2);
		player1Text.setText(person1);
		player2Text.setText(person2);
		
		try {
			setdieImage(dice.getDie().getTop());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rollButton.setOnAction(event -> {
			try {
				roll();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		holdButton.setOnAction(event -> {
			try {
				hold();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		update();
	}

	//Updating the text field with die rolled and which player is on current roll
	public void update() throws Throwable {
		
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
			
			String loser = (dice.getCurrent() != dice.getPlayer1())? 
							dice.getPlayer1().getName(): dice.getPlayer2().getName();
		
			if(rounds.nextRound(loser)) {
				
				URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(url);
	            AnchorPane newPane = (AnchorPane)loader.load();
	            Scene scene = new Scene(newPane);

	            WinningSceneController controller = loader.getController();
	            controller.initializeData(rounds.getWinner());
	   
	            Stage window = (Stage) mainPane.getScene().getWindow();  
	            window.setScene(scene);
	            window.show();
			}
			
			
			startRound(rounds.getPlayer1(), rounds.getPlayer2());
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
	private void roll() throws Throwable {
		
		dice.roll();
		update();
	}
	//calling on hold to save dice number
	private void hold() throws Throwable {
		dice.hold();
		update();
	}
	
	@FXML
	void onClickHome(MouseEvent event) throws IOException {
    	
    	Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	currWindow.close();
    }

	public void initializeData(ArrayList<Person> personList) throws Throwable {
    	
		rounds = new Rounds(personList);
    	startRound(rounds.getPlayer1(), rounds.getPlayer2());
    }

}
