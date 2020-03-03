package slogo.model.Commands.MultipleTurtleCommands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.Commands.Command;
import slogo.model.Turtle;

import java.util.ArrayList;

public abstract class MultipleTurtleCommand extends Command{
    public MultipleTurtleCommand(String name)
    {
        super(name);
    }

    public ObservableList<Turtle> generate1ActiveTurtleList(int turtleID) {
        ObservableList<Turtle> newTurtleList = FXCollections.observableArrayList(turtles);
        for (Turtle turtle : newTurtleList) {
            if (turtle.getId() != turtleID) {
                turtle.setActivated(false);
            } else {
                turtle.setActivated(true);
            }
        }
        return newTurtleList;
    }
}
