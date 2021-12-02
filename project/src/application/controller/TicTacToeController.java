package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.model.Person;
import application.model.TicTacToe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToeController {
	TicTacToe game = new TicTacToe();
	private ArrayList<Person> playerList;
	
    @FXML
    private Label ScoreHolder;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button homeButton;

    @FXML
    private Label turnText;

    @FXML
    private Text Square10;

    @FXML
    private Text Square21;

    @FXML
    private Text Square00;

    @FXML
    private Text Square11;

    @FXML
    private Text Square22;

    @FXML
    private Text Square01;

    @FXML
    private Text Square12;

    @FXML
    private Text Square02;

    @FXML
    private Text Square20;
    
    @FXML
    void Fill00(MouseEvent event) {
    	processTurn(0,0);
    }

    @FXML
    void Fill01(MouseEvent event) {
    	processTurn(0,1);
    }

    @FXML
    void Fill02(MouseEvent event) {
    	processTurn(0,2);
    }

    @FXML
    void Fill11(MouseEvent event) {
    	processTurn(1,1);
    }

    @FXML
    void Fill10(MouseEvent event) {
    	processTurn(1,0);
    }

    @FXML
    void Fill12(MouseEvent event) {
    	processTurn(1,2);
    }

    @FXML
    void Fill20(MouseEvent event) {
    	processTurn(2,0);
    }

    @FXML
    void Fill21(MouseEvent event) {
    	processTurn(2,1);
    }

    @FXML
    void Fill22(MouseEvent event) {
    	processTurn(2,2);
    }
    //returns the user to the home screen
    @FXML
    void onClickHome(MouseEvent event) throws IOException {
    	
    	Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	currWindow.close();
    }

    //arguement for initialize will be an arraylist of players, runs the game until arraylist has 1 player left which is the winner
    void updateDisplay() {

    	resetBoard();
    	
    	//initialize the game with the users, users are passed in from the add players screen
    	if(playerList.size() == 1) {
    		try {
			
				URL url;
				url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(url);
	            AnchorPane newPane = (AnchorPane)loader.load();
	            Scene scene = new Scene(newPane);

	            WinningSceneController controller = loader.getController();
				controller.initializeData(playerList.get(0));

	            Stage window = (Stage) anchorPane.getScene().getWindow();  
	            window.setScene(scene);
	            window.show();
	            
			} catch (IOException e) {
					e.printStackTrace();
			}
    	}
    	else {
    	game.initializeGame(playerList.get(0), playerList.get(1));
    	//if its the first game (both wins are zero)
    	if(game.showP1Score() == 0 && game.showP2Score() == 0) {
    	if(game.firstTurn()) 
    		turnText.setText(playerList.get(0).getName() + " P1 goes first");
    	else
    		turnText.setText(playerList.get(1).getName() + " P2 goes first");
    	}
    	else {
    		if(game.getTurn())
    			turnText.setText(playerList.get(0).getName() + " P1 goes first");
    		else
    			turnText.setText(playerList.get(1).getName() + " P2 goes first");
    	}
    	}
    }
    
    //resets the board to be empty
    @FXML
    void resetBoard() {
    	Square00.setText("");
    	Square01.setText("");
    	Square02.setText("");
    	Square10.setText("");
    	Square11.setText("");
    	Square12.setText("");
    	Square20.setText("");
    	Square21.setText("");
    	Square22.setText("");
    }
    
    //processes the turn by reading the row and column of the square selected by the player
    public void processTurn(int row, int column){
    	if(game.getTurn()) {
    		if(game.checkValidMove(row, column)) {
    			game.addMove(row, column, 'X');
    			playMoveSound();
    			fillSquare(row, column, "X");
    			if(game.checkWinner()) {
    				playerList.remove(1);
    				ScoreHolder.setText("Score: P1 - " + game.showP1Score() + " P2 - " + game.showP2Score() + " Ties - " + game.showTies());
    				turnText.setText(playerList.get(0).getName() + " is the winner!");
    				playWinSound();
    				updateDisplay();
    			}
    			else if(game.checkTie()) {
    				ScoreHolder.setText("Score: P1 - " + game.showP1Score() + " P2 - " + game.showP2Score() + " Ties - " + game.showTies());
    				turnText.setText("Tie!");
    				updateDisplay();
    			}
    			else {
    			game.nextTurn();
    			turnText.setText(playerList.get(1).getName() + " turn");
    			}
    		}
    	}
    	else {
    		if(game.checkValidMove(row, column)) {
    			game.addMove(row, column, 'O');
    			playMoveSound();
    			fillSquare(row, column, "O");
    			if(game.checkWinner()) {
    				playerList.remove(0);
    				ScoreHolder.setText("Score: P1 - " + game.showP1Score() + " P2 - " + game.showP2Score() + " Ties - " + game.showTies());
    				turnText.setText(playerList.get(0).getName() + " is the winner!");
    				playWinSound();
    				updateDisplay();
    			}
    			else if(game.checkTie()) {
    				ScoreHolder.setText("Score: P1 - " + game.showP1Score() + " P2 - " + game.showP2Score() + " Ties - " + game.showTies());
    				turnText.setText("Tie!");
    				updateDisplay();
    			}
    			else {
    			game.nextTurn();
    			turnText.setText(playerList.get(0).getName() + " turn");
    			}	
    		}
    	}
    }
    //fills the square with either and X or an O
    public void fillSquare(int row, int column, String s) {
    	switch(row*10 + column) {
    	case 0:
    		Square00.setText(s);
    		break;
    	case 1:
    		Square01.setText(s);
    		break;
    	case 2:
    		Square02.setText(s);
    		break;
    	case 10:
    		Square10.setText(s);
    		break;
    	case 11:
    		Square11.setText(s);
    		break;
    	case 12:
    		Square12.setText(s);
    		break;
    	case 20:
    		Square20.setText(s);
    		break;
    	case 21:
    		Square21.setText(s);
    		break;
    	case 22:
    		Square22.setText(s);
    		break;
    	default:
    		
    	}
    }
    //play sounds for winning and for inputting a move
    public void playWinSound() {
    	String musicFile = "start.mp3";
    	Media sound = new Media(new File("sounds/" + musicFile).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.setVolume(0.1);
    	mediaPlayer.play();
    }
    public void playMoveSound() {
    	String musicFile = "move.mp3";
    	Media sound = new Media(new File("sounds/" + musicFile).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.setVolume(0.1);
    	mediaPlayer.play();
    }
    //initialize the person arraylist
    public void initializeData(ArrayList<Person> personList) {
    	this.playerList = new ArrayList<Person>();
    	for(Person person: personList)
    		playerList.add(person);
    	updateDisplay();
    }

}
