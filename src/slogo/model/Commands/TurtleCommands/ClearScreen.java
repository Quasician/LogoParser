package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class ClearScreen extends TurtleCommand {

  public ClearScreen(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    //have to erase the turtle lines

    for(Turtle turtle: turtles) {
      double distanceTravelled = distanceFormula(turtle.getX(), turtle.getY(), 0, 0);
      moveTurtleTo(turtle.getId(),0, 0);
      commandNode.setResult("" + distanceTravelled);
      turtle.setDegree(0);

      turtle.clearScreenProperty().set(true);
      turtle.clearScreenProperty().set(false);
    }
  }
}
