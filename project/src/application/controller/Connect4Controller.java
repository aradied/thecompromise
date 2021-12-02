package application.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import application.model.Person;
import application.model.Rounds;

public class Connect4Controller {

	private Rounds rounds;
	private static final int COLUMNS=7;
	private static final int ROWS=6;
	private static final int CIRCLE_DIAMETER=80;
	private static final String discColor1="#ffa100";
	private static final String discColor2="#1010BC";

	private boolean isPlayerOneTurn = true;
	
	private Disc[][] insertedDiscsArray = new Disc[ROWS][COLUMNS];//For structural changes

	private boolean isAllowedToInsert = true;

    @FXML
    private Button homeButton;
	
    @FXML
    private Label player1Label;
	
    @FXML
    private Label player2Label;
	
	@FXML
	public GridPane rootGridPane;

	@FXML
	public Pane insertedDiscPane;

	@FXML
	public Label playerNameLabel;

	@FXML
	void onClickHome(MouseEvent event) throws IOException {
    	
    	Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	currWindow.close();
    }
	
	public void createPlayground(){
		//Platform.runLater(() -> setNamesButton.requestFocus());

		Shape rectangleWithHoles=createGameStructureGrid();
		//now place this rectangle on pane which is rootGridPane
		rootGridPane.add(rectangleWithHoles,0,0);

		List<Rectangle> rectangleList=createClickableColumns();
		for (Rectangle rectangle:rectangleList){
			rootGridPane.add(rectangle,0,0);
		}

	}

	private Shape createGameStructureGrid(){
		Shape rectangleWithHoles=new Rectangle((COLUMNS+1)*CIRCLE_DIAMETER,(ROWS+1)*CIRCLE_DIAMETER);

		for (int row=0;row<ROWS;row++){
			for (int col=0;col<COLUMNS;col++){
				Circle circle=new Circle();
				circle.setRadius(CIRCLE_DIAMETER/2);
				circle.setCenterX(CIRCLE_DIAMETER/2);
				circle.setCenterY(CIRCLE_DIAMETER/2);
				circle.setSmooth(true);
				circle.setTranslateX(col*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);
				circle.setTranslateY(row*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);
				rectangleWithHoles=Shape.subtract(rectangleWithHoles,circle);
			}
		}

		rectangleWithHoles.setFill(Color.WHITE);
		return rectangleWithHoles;
	}

	private List<Rectangle> createClickableColumns(){

		List<Rectangle> rectangleList=new ArrayList<>();
		for (int col=0;col<COLUMNS;col++){
			Rectangle rectangle=new Rectangle(CIRCLE_DIAMETER,(ROWS+1)*CIRCLE_DIAMETER);
			rectangle.setFill(Color.TRANSPARENT);
			rectangle.setTranslateX(col*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);

			rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.valueOf("#eeeeee66")));
			rectangle.setOnMouseExited(event -> rectangle.setFill(Color.TRANSPARENT));

			final int column=col;//because of lambda expression
			rectangle.setOnMouseClicked(event -> {
				if (isAllowedToInsert) {
					isAllowedToInsert=false;//To avoid multiple disc fall at a time
					insertDisc(new Disc(isPlayerOneTurn), column);
				}
			});
			rectangleList.add(rectangle);
		}

		return rectangleList;
	}

	private void insertDisc(Disc disc,int column){

		int row=ROWS-1;
		while (row >= 0){

			if (getDiscIfPresent(row,column)==null)
				break;
			row--;
		}
		if (row<0)  //If it is full,we cannot insert anymore disc
			return;

		insertedDiscsArray[row][column]=disc;  //For Structural changes for  developers
		
		insertedDiscPane.getChildren().add(disc);//For visual changes

		disc.setTranslateX(column*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);

		int currRow=row;
		TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.4),disc);
		translateTransition.setToY(row*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);

		//Now need to toggle between the player
		translateTransition.setOnFinished(event -> {
			 isAllowedToInsert=true;
			if(gameEnded(currRow,column)){

				gameOver();
				return;//no further check if one player wins the game
			}

			isPlayerOneTurn =! isPlayerOneTurn;//Player one turn become player two turn
			playerNameLabel.setText(isPlayerOneTurn? rounds.getPlayer1() : rounds.getPlayer2());
		});

		translateTransition.play();
	}

	private boolean gameEnded(int row, int column){

		//Vertical Points
		//A small example: player has inserted his last disc at row=2 , column=3
		//
		//index of each element present in column [row][column]:  0,3   1,3   2,3   3,3   4,3   5,3-->Poind2D
		//notice same column of 3.

		List<Point2D> verticalPointes=IntStream.rangeClosed(row-3,row+3)  //range of row values= 0,1,2,3,4,5
										.mapToObj(r-> new Point2D(r,column))  //0,3  1,3  2,3   3,3  4,3  5,3 ==> Point2D  x,y
										.collect(Collectors.toList());

		List<Point2D> horizontalPoints=IntStream.rangeClosed(column-3,column+3)
				.mapToObj(col-> new Point2D(row,col))
				.collect(Collectors.toList());

		Point2D startPoint1 =new Point2D(row-3,column+3);
		List<Point2D> digonal1Point=    IntStream.rangeClosed(0,6)
										.mapToObj(i-> startPoint1.add(i,-i))
										.collect(Collectors.toList());

		Point2D startPoint2 =new Point2D(row-3,column-3);
		List<Point2D> digonal2Point=    IntStream.rangeClosed(0,6)
										.mapToObj(i-> startPoint2.add(i,i))
										.collect(Collectors.toList());


		boolean isEnded=checkCombination(verticalPointes) || checkCombination(horizontalPoints)
				                                          || checkCombination(digonal1Point)
				                                          || checkCombination(digonal2Point);

		return isEnded;
	}

	private boolean checkCombination(List<Point2D> points) {
		int chain = 0;

		for (Point2D point: points) {

			int rowIndexForArray = (int) point.getX();
			int columnIndexForArray = (int) point.getY();

			//getting disc at particular row and column
			Disc disc = getDiscIfPresent(rowIndexForArray, columnIndexForArray);

			if (disc != null && disc.isPlayerOneMove == isPlayerOneTurn) {
				// if the last inserted Disc belongs to the current player
				chain++;
				if (chain == 4) {
					return true;
				}
			} else {
				chain = 0;
			}
		}

		return false;//as we haven't got the combination
	}

	public Disc getDiscIfPresent(int row,int column){  //To prevent ArrayIndexOutOfBoundIndex exception
		if (row >= ROWS || row < 0 || column >= COLUMNS || column < 0)  // If row or column index is invalid
			return null;

		return insertedDiscsArray[row][column];//return element at this position within our array
	}

	private void gameOver(){

		if(rounds.nextRound(isPlayerOneTurn ? rounds.getPlayer2() : rounds.getPlayer1())) {
			try {
				
				URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
				
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(url);
	            AnchorPane newPane = (AnchorPane)loader.load();
	            Scene scene = new Scene(newPane);

	            WinningSceneController controller = loader.getController();
				controller.initializeData(rounds.getWinner());
				
	   
	            Stage window = (Stage) rootGridPane.getScene().getWindow();  
	            window.setScene(scene);
	            window.show();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
		
		startRound(rounds.getPlayer1(), rounds.getPlayer2());
	}

	public void startRound(String player1, String player2) {
		
		insertedDiscPane.getChildren().clear(); //Remove all Inserted disc from pane
		//Now these loops will structurally make all elements of insertedDiscArray back to null
		for (int row = 0; row <insertedDiscsArray.length ; row++) {
			for (int column = 0; column < insertedDiscsArray[row].length; column++) {
				insertedDiscsArray[row][column] = null;
			}
		}
		isPlayerOneTurn=true;//let player one start the game
		
		player1Label.setText(player1);
		player2Label.setText(player2);
		playerNameLabel.setText(player1);

		createPlayground();//prepare a fresh playground
	}

	private static class Disc extends Circle{

		private final boolean isPlayerOneMove;
		public Disc(boolean isPlayerOneMove){
			this.isPlayerOneMove=isPlayerOneMove;

			setRadius(CIRCLE_DIAMETER/2);
			setFill(isPlayerOneMove? Color.valueOf(discColor1) : Color.valueOf(discColor2));
			setCenterX(CIRCLE_DIAMETER/2);
			setCenterY(CIRCLE_DIAMETER/2);
		}
	}

	public void initializeData(ArrayList<Person> personList) {

    	rounds = new Rounds(personList);
    	startRound(rounds.getPlayer1(), rounds.getPlayer2());
    }

}
