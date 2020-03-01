package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class ShowTurtle extends TurtleCommand {

  private static final String RETURN = "1";

  public ShowTurtle(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    for(Turtle activeTurtle: activatedTurtles)
    {
      activeTurtle.show();
      commandNode.setResult(RETURN);
    }
  }
}
