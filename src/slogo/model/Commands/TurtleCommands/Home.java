package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class Home extends TurtleCommand {

  public Home(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double distanceTravelled = distanceFormula(turtle.getX(), turtle.getY(), 0, 0);
    moveTurtleTo(0, 0);

    turtle.setDegree(0);
    commandNode.setResult("" + distanceTravelled);
  }
}
