package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class HideTurtle extends TurtleCommand {

  private static final String RETURN = "0";

  public HideTurtle(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    changeIsShowing(false);
    commandNode.setResult(RETURN);
  }
}