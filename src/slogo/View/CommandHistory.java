package slogo.View;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import slogo.Main;
import slogo.model.CommandParser;

public class CommandHistory implements HistoryView{
    private ResourceBundle myResources = Main.MY_RESOURCES;
    private ListView historyWindow;
    private static final Color TEXT_COLOR = Color.BLACK;
    private HBox newCommand;
    private ViewButton runButton;
    private Label commandEntered;
    private static final int HEIGHT = 310;
    private static final int ONE_CONSTANT = 1;
    private static final int NEW_HEIGHT = 200;
    private static final int WIDTH = 300;
    private static final int PLAY_IMAGE_SIZE = 10;
    private static final int PLAY_IMAGE_SIZE_IN_HBOX = 20;
    private CommandParser pars;
    private static final int FONT_SIZE=0;
    private static final int SPACING=5;
    private ArrayList<String> commandValues;
    private static final String STYLE = "Style";
    private static final String RUN_BUTTON= "PlayImage";
    private static final String EMPTY ="";

    public CommandHistory(CommandParser parser){
        commandValues = new ArrayList<>();
        historyWindow=new ListView();
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(myResources.getString(STYLE));
        historyWindow.setPrefHeight(HEIGHT);
        historyWindow.setPrefWidth(WIDTH);
        pars=parser;
    }

    protected List<String> getCommandListCopy() {
        List<String> copy = new ArrayList<String>(commandValues);
        return copy;
    }

    protected void removeCommand(){
        commandValues.remove(commandValues.size() - ONE_CONSTANT);
        }


    protected void makeBox(String StringRepresentation){
        runButton=new ViewButton(EMPTY,  PLAY_IMAGE_SIZE_IN_HBOX, PLAY_IMAGE_SIZE_IN_HBOX,FONT_SIZE);
        setImage(runButton, myResources.getString(RUN_BUTTON));
        commandValues.add(StringRepresentation);
        newCommand= new HBox(SPACING);
        newCommand.setMaxSize(WIDTH,NEW_HEIGHT);
        commandEntered=new Label(StringRepresentation);
        commandEntered.setTextFill(TEXT_COLOR);
        newCommand.getChildren().addAll(runButton,commandEntered);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getItems().add(newCommand);
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
