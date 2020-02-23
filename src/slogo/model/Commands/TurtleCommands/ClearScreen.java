package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class ClearScreen extends TurtleCommand {

  public ClearScreen(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    //have to erase the turtle lines
    clearScreenCalled = true;

    int distanceTravelled = (int)distanceFormula(turtle.getX(), turtle.getY(), 0, 0);
    moveTurtleTo(0, 0);
    commandNode.setData("" + distanceTravelled);

    clearScreenCalled = false;
  }
}
