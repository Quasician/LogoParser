package slogo.View;

import static javafx.scene.text.TextAlignment.CENTER;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import slogo.Main;

public class CommandHistory {
    private static VBox historyWindow;
    private static final Color textColor= Color.BLACK;
    private HBox newCommand;
    private CustomButton runButton;
    private Label commandEntered;
    private static final String imgValue= "Play.png";
    private static final int spacing=300;
    private static final int BoxSpacing=300;
    private ArrayList<String> Values;
    private static final String style = "-fx-background-color: rgba(0, 0,0, 0.5);";
    private ResourceBundle myResources = Main.myResources;

    public CommandHistory(){
        Values= new ArrayList<>();
        historyWindow=new VBox(5);
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(style);
        historyWindow.setPrefHeight(300.0);
        historyWindow.setPrefWidth(300.0);
        historyWindow.setMargin(historyWindow,new Insets(10,5,10,0));
    }

    private void runCommand(HBox cellForWindow){
        //run the command in the box of the runButton using the backend
        //doCommand(Values.get(cellForWindow.getAccessibleText()));
    }

    protected void makeBox(String StringRepresentation){
        runButton=new CustomButton();
        runButton.setMaxHeight(BoxSpacing);
        runButton.setMaxWidth(spacing);
        runButton.setImage(runButton, myResources.getString("PlayImage"));
        Values.add(StringRepresentation);
        newCommand= new HBox(5);
        newCommand.setMaxSize(300,200);
        commandEntered=new Label(StringRepresentation);
        commandEntered.setTextFill(textColor);
        newCommand.getChildren().addAll(runButton,commandEntered);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getChildren().add(newCommand);
        runButton.setOnAction(actionEvent -> runCommand(newCommand));
    }

    public static VBox returnScene(){
        return historyWindow;
    }
}
