package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * Assumption: turtle will always move clockwise to the new angle
 * unfinished
 */
public class SetTowards extends TurtleCommand {
  private double x;
  private double y;

  public SetTowards(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    x = Double.parseDouble(getParamList().get(0));
    y = Double.parseDouble(getParamList().get(1));

    for(Turtle activeTurtle: activatedTurtles) {
      double angle = Math.toDegrees(Math.atan2(x - activeTurtle.getX(), y - activeTurtle.getY()));

      if (angle < 0)
        angle += 360;

      activeTurtle.setDegree(angle);
      double returnValue = Math.abs(activeTurtle.getDegree() - angle);
      commandNode.setResult("" + returnValue);
    }
  }
}
