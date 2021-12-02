/**
 * LoginController class shows the Login scene where the user 
 * inputs a username and password to login and advance to the next scene.
 * 
 * Fall 2021
 */

package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.model.Person;
import javafx.fxml.Initializable;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.lang.Math;

public class MenuController implements Initializable {
	
	private final int MAX_PEOPLE = 10;
	private final String ADD = "add";
	private final String REMOVE = "remove";
	private ArrayList<Person> personList;
	
    @FXML
    private Circle circleBase;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button selectButton;
    
    @FXML
    void onClickSelectButton(MouseEvent event) {

    	if(personList.size() < 2) return;
    	
    	try {
        	URL url = new File("src/application/view/SelectMenu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            Stage currWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
            
            SelectController controller = loader.getController();
            controller.initializeData(personList, currWindow);
            
            Stage window = new Stage();
            window.setScene(scene);
            window.showAndWait();
            currWindow.show();
          
            
        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
    }
    
    @FXML
    void OnClickAddButton(MouseEvent event) throws InterruptedException {

        Button button;
        int index = 0, previusSize = 0;
        double radius = 40;
        
        previusSize = personList.size();
        
        // Open the add person window as a pop-up
        try {
        	URL url = new File("src/application/view/AddPersonWindow.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            AnchorPane newPane = (AnchorPane)loader.load();
            Scene scene = new Scene(newPane);

            AddPersonController controller = loader.getController();
            controller.initializeData(personList);
            
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(scene);
            window.showAndWait();
            
        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
        
        if(previusSize == personList.size()) return;
        
        button = new Button();
        setRadius(button, radius);
        button.setShape(new Circle(radius));
        relocate(button, getCenterX(addButton), getCenterY(addButton));
        button.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()), null, null)));
        button.setFont(addButton.getFont());
        button.setText(("" + personList.get(personList.size()-1).getName().charAt(0)).toUpperCase());
        
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){	
        	
			public void handle(MouseEvent event) {
				
				menuPane.getChildren().remove(event.getTarget());
				updateScreen(REMOVE);
			}
		});
        
        index = menuPane.getChildren().indexOf(addButton);
        menuPane.getChildren().add(index, button);
        
        updateScreen(ADD);
    }
    
    // Updates the position of the circles and animates their movement 
    public void updateScreen(final String MODE) {
    	
    	double currAngle, angle, targetX = 0, targetY = 0;
    	Button button;
        int i = 0;
    	
    	ObservableList<Node> nodeList = menuPane.getChildren();
         
        if(nodeList.size() >= MAX_PEOPLE && MODE.equals(ADD)) {
        	
        	addButton.setVisible(false);
        	return;
        }
        
        if(nodeList.size() == MAX_PEOPLE - 1 && MODE.equals(REMOVE)) {
        	
        	addButton.setVisible(true);
        }
        
        for(Node node:nodeList) {
            
        	button = (Button)node;
            
            angle = (2 * Math.PI / nodeList.size()) * i - Math.PI / 2;
            angle = (angle < 0)? angle + Math.PI * 2 : angle;
            
            targetX = Math.cos(angle) * circleBase.getRadius() + circleBase.getLayoutX();
            targetY = Math.sin(angle) * circleBase.getRadius() + circleBase.getLayoutY();
            i++;
            
            currAngle = Math.atan2(getCenterY(button) - circleBase.getLayoutY(), getCenterX(button) - circleBase.getLayoutX());
            currAngle = (currAngle < 0)? currAngle + Math.PI * 2 : currAngle;
            
            // Skip subsequent steps if circle is already in destined position
            if(Math.abs(currAngle - angle) < 0.1) continue;
            
            // CircleAnimation moves a circle from its current position to a given position along the circumference
            class CircleAnimation extends AnimationTimer {
            	
            	public double toAngle, toX, toY;
            	public Button button;
            	
            	public CircleAnimation(double toX, double toY, Button button) {
            		
            		toAngle = Math.atan2((toY - circleBase.getLayoutY()) / getRadius(button), (toX - circleBase.getLayoutX()) / getRadius(button));
            		toAngle = (toAngle < 0)? toAngle + Math.PI * 2 : toAngle;
            		
            		this.toX = toX;
            		this.toY = toY;
            		this.button = button;
            	}
            	
            	@Override
            	public void handle(long time) {

            		double x, y, currAngle, difference;
                    currAngle = Math.atan2(getCenterY(button) - circleBase.getLayoutY(), getCenterX(button) - circleBase.getLayoutX());
                    currAngle = (currAngle < 0)? currAngle + Math.PI * 2 : currAngle;
                    
                    difference = toAngle - currAngle;
                    
                    if(Math.abs(getCenterX(button) - toX) < 0.1 && Math.abs(getCenterY(button) - toY) < 0.1) this.stop();
                	if(Math.abs(currAngle - toAngle) < 0.1) this.stop();
                	
                    if(Math.abs(difference) < (2 * Math.PI - Math.abs(difference))) {
                    	
                    	if(difference > 0) currAngle += Math.PI * 2 / 100;
                    	else currAngle -= Math.PI * 2 / 100;
                    }
                    else {
 
                    	if(currAngle > toAngle) currAngle += Math.PI * 2 / 100;
                    	else currAngle -= Math.PI * 2 / 100;
                    }
                   
                    x = Math.cos(currAngle) * circleBase.getRadius() + circleBase.getLayoutX();
                    y = Math.sin(currAngle) * circleBase.getRadius() + circleBase.getLayoutY();
                    
                    relocate(button, x, y);
    			}
            }
            
            CircleAnimation animation = new CircleAnimation(targetX, targetY, button);
            
            animation.start();
        }
    }
    
    // Get x coordinate of the button's center
    public double getCenterX(Button button) {
    	
    	return button.getLayoutX() + button.getPrefWidth() / 2;
    }
    
    // Get Y coordinate of the button's center
    public double getCenterY(Button button) {
    	
    	return button.getLayoutY() + button.getPrefHeight() / 2;
    }
    
    // Get radius of button
    public double getRadius(Button button) {
    	
    	return button.getPrefHeight() / 2;
    }
    
    // Set radius of button
    public void setRadius(Button button, double radius) {
    	
	 	button.setPrefHeight(radius*2);
	 	button.setPrefWidth(radius*2);
    }
    
    // Relocate the button to the given x and y coordinates
    public void relocate(Button button, double x, double y) {
    	
    	button.relocate(x - button.getPrefWidth() / 2, y - button.getPrefHeight() / 2);
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	double angle, x , y;
    	Button button;
    	int i = 0;
    	double radius = 40;
    	personList = new ArrayList<Person>();
    	
    	addButton.setShape(new Circle(radius));
    	try {  
    		Image image = new Image(new File("Images/plus.jpg").toURI().toURL().toString());
    		
    		addButton.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, true))));
    	} 
    	catch (MalformedURLException e) { e.printStackTrace(); }
    	
    	ObservableList<Node> buttonList = menuPane.getChildren();

    	for(Node node:buttonList) {
    		
    		button = (Button)node;
    		angle = ( 2 * Math.PI / buttonList.size()) * i - Math.PI / 2;
    		
    		x = Math.cos(angle) * circleBase.getRadius() + circleBase.getLayoutX();
    		y = Math.sin(angle) * circleBase.getRadius() + circleBase.getLayoutY();
    		
    		relocate(button, x, y);
    		
    		i++;
    	}
    }
}
