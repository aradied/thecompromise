package application.controller;

import java.util.ArrayList;
import application.model.Person;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddPersonController {

	private ArrayList<Person> personList;
	
    @FXML
    private Button cancelButton;

    @FXML
    private TextField placeField;

    @FXML
    private TextField nameField;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void onClickAddButton(MouseEvent event) {

    	if(nameField.getText().equals("") || placeField.getText().equals("")) return;
    	
    	personList.add(new Person(nameField.getText(), placeField.getText()));
    	
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void onClickCancelButton(MouseEvent event) {

    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close(); 	
    }
    
    public void initializeData(ArrayList<Person> personList) {
    	
    	this.personList = personList;
    }
}
