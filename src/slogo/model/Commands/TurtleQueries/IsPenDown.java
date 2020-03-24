package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Class for IsPenDown command
 */
public class IsPenDown extends TurtleQuery {

  public IsPenDown(String name) {
    super(name);
  }

  /**
   * If pen down, return 1 else 0
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    if (turtles.get(0).isPenDown())
      commandNode.setResult(TRUE);
    else
      commandNode.setResult(FALSE);
  }
}

