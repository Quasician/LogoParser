package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class PenUp extends TurtleCommand {

  private static final String RETURN = "0";

  public PenUp(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    for(Turtle activeTurtle: activatedTurtles) {
      activeTurtle.penUp();
      commandNode.setResult(RETURN);
    }
  }
}
