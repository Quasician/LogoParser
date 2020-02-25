package slogo.View;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import slogo.Main;

public class CommandHistory {
    private ResourceBundle myResources = Main.myResources;
    private VBox historyWindow;
    private static final Color TEXT_COLOR = Color.WHITE;
    private HBox newCommand;
    private ViewButton runButton;
    private Label commandEntered;
    private static final int SPACING = 300;
    private static final int BOX_SPACING = 300;
    private static final int PLAY_IMAGE_SIZE = 10;

    private ArrayList<String> commandValues;
    private static final String STYLE = "-fx-background-color: rgba(0, 0,0, 0.5);";


    public CommandHistory(){
        commandValues = new ArrayList<>();
        historyWindow=new VBox(5);
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(STYLE);
        historyWindow.setPrefHeight(310.0);
        historyWindow.setPrefWidth(300.0);
        historyWindow.setMargin(historyWindow,new Insets(10,5,10,0));
    }

    private void runCommand(HBox cellForWindow){
        //run the command in the box of the runButton using the backend
        //doCommand(commandValues.get(cellForWindow.getAccessibleText()));
    }

    protected void makeBox(String StringRepresentation){
        runButton=new ViewButton("", 2* PLAY_IMAGE_SIZE, 2* PLAY_IMAGE_SIZE, 0);
        setImage(runButton, myResources.getString("PlayImage"));
        commandValues.add(StringRepresentation);
        newCommand= new HBox(5);
        newCommand.setMaxSize(300,200);
        commandEntered=new Label(StringRepresentation);
        commandEntered.setTextFill(TEXT_COLOR);
        newCommand.getChildren().addAll(runButton,commandEntered);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getChildren().add(newCommand);
        runButton.setOnAction(actionEvent -> runCommand(newCommand));
    }

    private void setImage(ViewButton button, String image){
        Image img = new Image(image);
        ImageView buttonImage= new ImageView(img);
        buttonImage.setFitHeight(PLAY_IMAGE_SIZE);
        buttonImage.setFitWidth(PLAY_IMAGE_SIZE);
        button.setGraphic(buttonImage);

    }
    public VBox returnScene(){
        return historyWindow;
    }
}
