package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import application.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectController {

	private ArrayList<Person> personList;
	Stage prevWindow;
	
    @FXML
    private AnchorPane mainPane;

    @FXML
    void onClickTicTaToe(MouseEvent event) throws InterruptedException {

    	try {
        	URL url = new File("src/application/view/TicTacToe.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            TicTacToeController controller = loader.getController();
            controller.initializeData(personList);
            
            Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
            //currWindow.hide();
            
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(scene);
            prevWindow.hide();
            window.showAndWait();
            currWindow.close();

        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
    	
    }

    @FXML
    void onClickDice(MouseEvent event) {

    	try {
        	URL url = new File("src/application/view/Dice.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            DiceController controller = loader.getController();
            try {
				controller.initializeData(personList);
			} catch (Throwable e) {
				e.printStackTrace();
			}
            
            Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
            
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(scene);
            prevWindow.hide();
            window.showAndWait();
            currWindow.close();
            
        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
    }

    @FXML
    void onClickConnect(MouseEvent event) {

    	try {
        	URL url = new File("src/application/view/Connect.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            GridPane newPane = (GridPane)loader.load();
            Scene scene = new Scene(newPane);

            Connect4Controller controller = loader.getController();
            controller.initializeData(personList);
            
            Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
   
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(scene);
            prevWindow.hide();
            window.showAndWait();
            currWindow.close();
            
        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
    }

    @FXML
    void onClickGuessing(MouseEvent event) {

    	try {
        	URL url = new File("src/application/view/Guess.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            RandomNumberGameController controller = loader.getController();
            controller.initializeData(personList);
            
            Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
   
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(scene);
            prevWindow.hide();
            window.showAndWait();
            currWindow.close();
            
        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
    }
    
    public void initializeData(ArrayList<Person> personList, Stage prevWindow) {
    	
    	this.personList = personList;
    	this.prevWindow = prevWindow;
    }
}
