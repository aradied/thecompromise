/*
 * This class handles the guessing game part of the application
 * displays a input field for each player and takes their guesses 
 * has button to generate a random number and compare inputs to number
 * nothing happens if no one is correct until someone matches the answer
 * sends winner information to winning scene
 */

package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Random;

import application.model.Person;

public class RandomNumberGameController {

	
	private ArrayList<Person> personList;
	
	@FXML
	private AnchorPane mainPane;
	
    @FXML
    private Label player5;

    @FXML
    private Label player6;

    @FXML
    private Label player7;

    @FXML
    private TextField player3guess;

    @FXML
    private Label player8;

    @FXML
    private Label player10;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label player3;

    @FXML
    private TextField player6guess;

    @FXML
    private Label player4;		

    @FXML
    private TextArea RandNumBox;

    @FXML
    private Label player9;

    @FXML
    private TextField player8guess;

    @FXML
    private Button homeButton;

    @FXML
    private TextField player5guess;

    @FXML
    private TextField player10guess;

    @FXML
    private TextField player2guess;

    @FXML
    private TextField player7guess;

    @FXML
    private TextField player1guess;

    @FXML
    private TextField player9guess;

    @FXML
    private TextField player4guess;
    
    @FXML
    private Label WinningName;
    
    
    @FXML
    void handleHomeButton(ActionEvent event) throws IOException {
        	
        	Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        	currWindow.close();
    }

    
    @FXML
    void HandleGenerateNumber(ActionEvent event) throws IOException {
    	Random rand = new Random();
    	int upperbound = 21;
    	int rand_num = rand.nextInt(upperbound);
    	String s = String.valueOf(rand_num);
    	RandNumBox.setText(s);
    	
    	if(player1guess.getText().matches(s)) {
					
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player1.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
					
    	}
		else if (player2guess.getText().matches(s)) {
    		
			URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player2.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player3guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player3.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player4guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player4.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player5guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player5.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player6guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player6.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player7guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player6.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player8guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player7.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else if (player9guess.getText().matches(s)) {
    		
    		URL url = new File("src/application/view/Winning_Scene.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            WinningSceneController controller = loader.getController();
            controller.initializeData(findPerson(player7.getText()));
   
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();  
            window.setScene(scene);
            window.show();
    	}
    	else {
    		WinningName.setText("No one won, play again!");
    	}
    }
    
    public Person findPerson(String name) {
    	
    	for(Person person: personList) {
    		if(person.getName().equals(name)) return person;
    	}
    	
    	return null;
    }
    
    public void initializeData(ArrayList<Person> personList) {
    	
    	int i;
    	this.personList = personList;
    	
    	ObservableList<Node> nodeList = mainPane.getChildren();
    	for(Node node: nodeList) {
    		
    		if(node.getId() != null) {
    			
    			if(node.getId().equals("") || node.getId().length() > 2) continue;
    			
    			i = Integer.parseInt(node.getId());
    			if( i > personList.size()) {
    				node.setVisible(false);
    				continue;
    			}
    			
    			try {
    				
    				if(i <= personList.size()) ((Label)node).setText(personList.get(i-1).getName());
    			} catch (Exception e) {
    				continue;
    			}
    		}
    	}
    }

}
