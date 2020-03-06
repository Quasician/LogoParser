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

    double returnValue = setTowards(x, y);
    commandNode.setResult("" + returnValue);
  }
}
