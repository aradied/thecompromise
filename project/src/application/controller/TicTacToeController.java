package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import application.model.Person;
import application.model.TicTacToe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicTacToeController {
	TicTacToe game = new TicTacToe();
	private ArrayList<Person> personList, playerList;
	
	int count = 0;
	
    @FXML
    private Label ScoreHolder;
    
    @FXML
    private AnchorPane anchorPane;

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
    //shows a pop out that tells the user if there was a tie or if a user won
    void showWinnerTie(String s) throws Exception {
    	
    	URL url = new File("src/application/view/WinnerScreen.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        anchorPane = (AnchorPane) loader.load();
        Scene scene = new Scene(anchorPane);
        Stage window = new Stage();
        WinnerScreenController ws = loader.getController();
        ws.initialize(s);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Popup");
        window.setScene(scene);
        window.showAndWait();
    }
    //arguement for initialize will be an arraylist of players, runs the game until arraylist has 1 player left which is the winner
    
    void updateDisplay() {

    	resetBoard();
    	
    	//initialize the game with the users, users are passed in from the add players screen
    	if(playerList.size() == 1) {
    		System.out.println("The winner is " + playerList.get(0).getName());
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
    				try {
						showWinnerTie("The winner is " + playerList.get(0).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				updateDisplay();
    			}
    			else if(game.checkTie()) {
    				ScoreHolder.setText("Score: P1 - " + game.showP1Score() + " P2 - " + game.showP2Score() + " Ties - " + game.showTies());
    				turnText.setText("Tie!");
    				try {
						showWinnerTie("A tie has occured!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
    				try {
						showWinnerTie("The winner is " + playerList.get(0).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				updateDisplay();
    			}
    			else if(game.checkTie()) {
    				ScoreHolder.setText("Score: P1 - " + game.showP1Score() + " P2 - " + game.showP2Score() + " Ties - " + game.showTies());
    				turnText.setText("Tie!");
    				try {
						showWinnerTie("A tie has occured!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
    		System.out.println("Error: Row and Column could not be resolved. Row: " + row + " Column: " + column);
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
    	
    	this.personList = personList;
    	
    	System.out.println(personList.size());
    	
    	for(Person person: personList)
    		playerList.add(person);
    	
    	updateDisplay();
    }

}
