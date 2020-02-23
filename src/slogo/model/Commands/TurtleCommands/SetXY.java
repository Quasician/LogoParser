package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class SetXY extends TurtleCommand {
  private int x;
  private int y;

  public SetXY(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    x = (int)Double.parseDouble(getParamList().get(0));
    y = (int)Double.parseDouble(getParamList().get(1));

    int distance = (int)distanceFormula(turtle.getX(), turtle.getY(), x, y);
    moveTurtleTo(x, y);

    commandNode.setData("" + distance);
  }
}
