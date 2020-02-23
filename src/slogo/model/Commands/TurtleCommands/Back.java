package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Back command: BACK pixels
 * Moves the turtle backward by "pixels" spaces in the direction it currently faces,
 * and returns "pixels"
 *
 */
public class Back extends TurtleCommand {

  private double distance;

  public Back(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    distance = Double.parseDouble(getParamList().get(0));
    commandNode.setData(distance + "");
    moveTurtle(backward, distance);
  }
}
