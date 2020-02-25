package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class ClearScreen extends TurtleCommand {

  public ClearScreen(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    //have to erase the turtle lines

    double distanceTravelled = distanceFormula(turtle.getX(), turtle.getY(), 0, 0);
    moveTurtleTo(0, 0);
    commandNode.setResult("" + distanceTravelled);
    turtle.setDegree(0);

    turtle.clearScreenProperty().set(true);
    turtle.clearScreenProperty().set(false);
  }
}
