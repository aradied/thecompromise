package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WinnerScreenController {
	//variables
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label TitleLabel;

    @FXML
    private Label displayWinnerTie;
    //initializes the pop up window with the winner and their name or text showing that there was a tie
    void initialize(String s) {
    displayWinnerTie.setText(s);
    
    }
}
