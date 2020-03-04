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
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class OutputView {
    private ResourceBundle myResources = Main.myResources;
    private ListView historyWindow;
    private static final Color TEXT_COLOR = Color.BLACK;
    private HBox newCommand;
    private ViewButton runButton;
    private Label commandEntered;
    private Label terminalCommand;
    private static final String textForTerminal="(Slogo) User-MBP:~ User$ ";
    private static final int SPACING = 300;
    private static final int BOX_SPACING = 300;
    private static final int PLAY_IMAGE_SIZE = 10;

    private ArrayList<String> commandValues;
    private static final String STYLE = "-fx-background-color: rgba(255, 255,255, 0.5);";

    public OutputView(){
        commandValues = new ArrayList<>();
        historyWindow=new ListView();
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(STYLE);
        historyWindow.setPrefHeight(310.0);
        historyWindow.setPrefWidth(300.0);
    }

    protected List<String> getCommandListCopy() {
        List<String> copy = new ArrayList<String>(commandValues);
        return copy;
    }


    protected void makeBox(String StringRepresentation){
        commandValues.add(StringRepresentation);
        newCommand= new HBox(5);
        newCommand.setMaxSize(500,200);
        commandEntered=new Label(StringRepresentation);
        terminalCommand= new Label(textForTerminal);
        commandEntered.setTextFill(TEXT_COLOR);
        newCommand.getChildren().addAll(terminalCommand,commandEntered);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getItems().addAll(newCommand);
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
