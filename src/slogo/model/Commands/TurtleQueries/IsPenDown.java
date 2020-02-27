package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

public class IsPenDown extends TurtleQuery {

  public IsPenDown(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    if (turtle.isPenDown())
      commandNode.setResult(TRUE);
    else
      commandNode.setResult(FALSE);
  }
}

