package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class HideTurtle extends TurtleCommand {

  private static final String RETURN = "0";

  public HideTurtle(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    turtle.hide();
    commandNode.setResult(RETURN);
  }
}