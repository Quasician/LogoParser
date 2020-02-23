package slogo.View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.model.Command;

import java.util.LinkedHashMap;

public class CommandHistory {
    private static VBox historyWindow;
    private HBox newCommand;
    private CustomButton runButton;
    private Label commandEntered;
    private static final String imgValue= "Play.png";
    private static final int spacing=10;
    private static final int BoxSpacing=5;
    private LinkedHashMap<String,Command> Values;

    public CommandHistory(){
        runButton=new CustomButton();
        Values= new LinkedHashMap<>();
        historyWindow=new VBox(spacing);
        runButton.setImage(runButton,imgValue);
    }

    private void runCommand(HBox cellForWindow){
        //run the command in the box of the runButton using the backend
        //doCommand(Values.get(cellForWindow.getAccessibleText()));
    }

    private void makeBox(Command command, String StringRepresentation){
        Values.put(StringRepresentation, command);
        newCommand= new HBox(BoxSpacing);
        commandEntered=new Label(StringRepresentation);
        newCommand.getChildren().addAll(commandEntered,runButton);
        newCommand.setAccessibleText(StringRepresentation);
        historyWindow.getChildren().add(newCommand);
        runButton.setOnAction(actionEvent -> runCommand(newCommand));
    }

    public static VBox returnScene(){
        return historyWindow;
    }
}
