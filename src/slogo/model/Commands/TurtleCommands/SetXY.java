package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class SetXY extends TurtleCommand {
  private double x;
  private double y;

  public SetXY(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    x = Double.parseDouble(getParamList().get(0));
    y = Double.parseDouble(getParamList().get(1));

    double distance = distanceFormula(turtle.getX(), turtle.getY(), x, y);
    moveTurtleTo(x, y);

    commandNode.setResult("" + distance);
  }
}
