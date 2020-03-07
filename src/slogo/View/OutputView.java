package slogo.View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class OutputView implements HistoryView {
    private ListView historyWindow;
    private static final Color TEXT_COLOR = Color.BLACK;
    private HBox newCommand;
    private Label commandEntered;
    private Label terminalCommand;
    private static final double WINDOW_HEIGHT = 310.0;
    private static final double WINDOW_WIDTH = 300.0;
    private static final double SPACING = 5;
    private static final double MAX_HEIGHT = 200;
    private static final double MAX_WIDTH = 500;
    private static final String TEXT_FOR_TERMINAL = "(Slogo) User-MBP:~ User$ ";
    private ArrayList<String> commandValues;
    private static final String STYLE = "-fx-background-color: rgba(255, 255,255, 0.5);";

    public OutputView() {
        commandValues = new ArrayList<>();
        historyWindow = new ListView();
        historyWindow.setBackground(Background.EMPTY);
        historyWindow.setStyle(STYLE);
        historyWindow.setPrefHeight(WINDOW_HEIGHT);
        historyWindow.setPrefWidth(WINDOW_WIDTH);
    }

    protected void makeBox(String StringRepresentation) {
        commandValues.add(StringRepresentation);
        newCommand = new HBox(SPACING);
        newCommand.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        commandEntered = new Label(StringRepresentation);
        terminalCommand = new Label(TEXT_FOR_TERMINAL);
        commandEntered.setTextFill(TEXT_COLOR);
        newCommand.getChildren().addAll(terminalCommand, commandEntered);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getItems().addAll(newCommand);
    }

    public ListView returnScene() {
        return historyWindow;
    }
}









































































