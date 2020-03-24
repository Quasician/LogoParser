package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * HideTurtle command
 */
public class HideTurtle extends TurtleCommand {

  private static final String RETURN = "0";

  public HideTurtle(String name) {
    super(name);
  }

  /**
   * Makes turtle hidden
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    changeIsShowing(false);
    commandNode.setResult(RETURN);
  }
}