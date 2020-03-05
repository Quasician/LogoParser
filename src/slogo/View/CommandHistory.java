package slogo.View;

import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import slogo.Main;
import slogo.model.CommandParser;

public class CommandHistory implements HistoryView{
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


    public CommandHistory(CommandParser parser){
        commandValues = new ArrayList<>();
        historyWindow=new ListView();
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(STYLE);
        historyWindow.setPrefHeight(310.0);
        historyWindow.setPrefWidth(300.0);
        // historyWindow.setMargin(historyWindow,new Insets(10,5,10,0));
        pars=parser;
    }

    protected List<String> getCommandListCopy() {
        List<String> copy = new ArrayList<String>(commandValues);
        return copy;
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
