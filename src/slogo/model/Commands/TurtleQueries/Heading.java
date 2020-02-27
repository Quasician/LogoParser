package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

public class Heading extends TurtleQuery {

  public Heading(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setResult("" + turtle.getDegree());
  }
}

