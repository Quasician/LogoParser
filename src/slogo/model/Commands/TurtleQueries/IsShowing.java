package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

public class IsShowing extends TurtleQuery {

  public IsShowing(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    if (turtle.isVisible()) {
      commandNode.setResult(TRUE);
    } else {
      commandNode.setResult(FALSE);
    }



  }
}
