package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * ShowTurtle command
 */
public class ShowTurtle extends TurtleCommand {

  private static final String RETURN = "1";

  public ShowTurtle(String name) {
    super(name);
  }

  /**
   * Make the turtle visible
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    changeIsShowing(true);
    commandNode.setResult(RETURN);
  }
}
