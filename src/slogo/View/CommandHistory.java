package slogo.View;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import slogo.model.Command;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CommandHistory {
    private static VBox historyWindow;
    private static final Color textColor= Color.WHITE;
    private HBox newCommand;
    private CustomButton runButton;
    private Label commandEntered;
    private static final String imgValue= "Play.png";
    private static final int spacing=10;
    private static final int BoxSpacing=5;
    private ArrayList<String> Values;
    private static final String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";

    public CommandHistory(){
        runButton=new CustomButton();
        runButton.setMaxHeight(BoxSpacing);
        runButton.setMaxWidth(spacing);
        Values= new ArrayList<>();
        historyWindow=new VBox(spacing);
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(style);
        //runButton.setImage(runButton,imgValue);
    }

    private void runCommand(HBox cellForWindow){
        //run the command in the box of the runButton using the backend
        //doCommand(Values.get(cellForWindow.getAccessibleText()));
    }

    private void makeBox(String StringRepresentation){
        Values.add(StringRepresentation);
        newCommand= new HBox(BoxSpacing);
        commandEntered=new Label(StringRepresentation);
        commandEntered.setTextFill(textColor);
        newCommand.getChildren().addAll(commandEntered,runButton);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getChildren().add(newCommand);
        runButton.setOnAction(actionEvent -> runCommand(newCommand));
    }

    public static VBox returnScene(){
        return historyWindow;
    }
}
