package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * SetTowards command
 * Assumption: turtle will always move clockwise to the new angle
 */
public class SetTowards extends TurtleCommand {
  private double x;
  private double y;

  public SetTowards(String name) {
    super(name);
  }

  /**
   * Set the heading of the turtle to face the input point
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    x = Double.parseDouble(getParamList().get(0));
    y = Double.parseDouble(getParamList().get(1));

    double returnValue = setTowards(x, y);
    commandNode.setResult("" + returnValue);
  }
}
