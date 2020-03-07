package slogo.model.Commands.MultipleTurtleCommands;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.Turtle;

import java.util.ArrayList;

public abstract class MultipleTurtleCommand extends Command {

  public MultipleTurtleCommand(String name) {
    super(name);
  }

  protected ObservableList<Turtle> generate1ActiveTurtleList(int turtleID) {
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

  protected void setResult(List<Boolean> currentTurtleStates, TreeNode commandNode, String[] commands, String finalValue) {
    int turtle = 0;
    for (Boolean bool : currentTurtleStates) {
      turtles.get(turtle).setActivated(bool);
      turtle++;
    }

    if (commands.length == 0) {
      commandNode.setResult("0");
    } else {
      commandNode.setResult(finalValue);
    }
  }

  protected String[] getCommandsArray() {
    return getParamList().get(1).split("\\s+");
  }

  protected String getAllCommands() {
    return getParamList().get(1).replaceFirst("\\[(.*?)\\]", "$1");
  }

  protected List<Boolean> getCurrentTurtleStates() {
    ArrayList<Boolean> currentTurtleStates = new ArrayList<>();
    for (Turtle turtle : turtles) {
      currentTurtleStates.add(turtle.isActivatedProperty().getValue());
    }
    return currentTurtleStates;
  }

  protected String getCondition() {
    return getParamList().get(0).replaceFirst("\\[(.*?)\\]", "$1");
  }
}
