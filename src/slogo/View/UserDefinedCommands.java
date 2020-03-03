package slogo.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import slogo.Main;
import slogo.model.CommandParser;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserDefinedCommands {
    private ResourceBundle myResources = Main.myResources;
    private ListView historyWindow;
    private static final Color TEXT_COLOR = Color.BLACK;
    private HBox newCommand;
    private ViewButton runButton;
    private Label commandEntered;
    private static final int SPACING = 300;
    private static final int BOX_SPACING = 300;
    private static final int PLAY_IMAGE_SIZE = 10;
    private CommandParser pars;

    private ArrayList<String> commandValues;
    private static final String STYLE = "-fx-background-color: rgba(0, 0,0, 0.5);";
    public UserDefinedCommands(CommandParser parser) {
        commandValues = new ArrayList<>();
        historyWindow = new ListView();
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(STYLE);
        historyWindow.setPrefHeight(310.0);
        historyWindow.setPrefWidth(300.0);
        // historyWindow.setMargin(historyWindow,new Insets(10,5,10,0));
        pars = parser;
    }
    private void setImage(ViewButton button, String image){
        Image img = new Image(image);
        ImageView buttonImage= new ImageView(img);
        buttonImage.setFitHeight(PLAY_IMAGE_SIZE);
        buttonImage.setFitWidth(PLAY_IMAGE_SIZE);
        button.setGraphic(buttonImage);

    }
    public ListView returnScene(){
        return historyWindow;
    }

    public Button returnButton() {
        return runButton;
    }

}







