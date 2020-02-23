package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

/**
 * Assumption: turtle will always move clockwise to the new angle
 * unfinished
 */
public class SetTowards extends TurtleCommand {
  private int x;
  private int y;

  public SetTowards(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    x = (int)Double.parseDouble(getParamList().get(0));
    y = (int)Double.parseDouble(getParamList().get(1));

    double angle = Math.toDegrees(Math.atan2(x - turtle.getX(), y - turtle.getY()));

    if (angle < 0)
      angle += 360;

    turtle.setDegree((int)angle);
    int returnValue = Math.abs(turtle.getDegree() - (int)angle);
    commandNode.setData("" + returnValue);
  }
}
