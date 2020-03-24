package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Class for IsShowing command
 */
public class IsShowing extends TurtleQuery {

  public IsShowing(String name) {
    super(name);
  }

  /**
   * Return 1 if showing, 0 else
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    if (turtles.get(0).isVisible()) {
      commandNode.setResult(TRUE);
    } else {
      commandNode.setResult(FALSE);
    }



  }
}
