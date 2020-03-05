package slogo.View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import slogo.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OutputView implements HistoryView {
    private ListView historyWindow;
    private static final Color TEXT_COLOR = Color.BLACK;
    private HBox newCommand;
    private Label commandEntered;
    private Label terminalCommand;
    private static final String textForTerminal="(Slogo) User-MBP:~ User$ ";

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

    public ListView returnScene(){
        return historyWindow;
    }

}
