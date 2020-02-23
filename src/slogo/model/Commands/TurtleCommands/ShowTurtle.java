package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class ShowTurtle extends TurtleCommand {

  private static final String RETURN = "1";

  public ShowTurtle(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    turtle.show();
    commandNode.setData(RETURN);
  }
}
