package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * PenDown command
 */
public class PenDown extends TurtleCommand {

  private static final String RETURN = "1";

  public PenDown(String name) {
    super(name);
  }

  /**
   * puts the pen down
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    changePen(true);
    commandNode.setResult(RETURN);
  }
}
