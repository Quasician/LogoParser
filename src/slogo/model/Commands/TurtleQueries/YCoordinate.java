package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

public class YCoordinate extends TurtleQuery {

  public YCoordinate(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setResult("" + turtles.get(0).getY());
  }
}