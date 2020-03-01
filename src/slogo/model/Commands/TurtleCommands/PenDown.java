package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class PenDown extends TurtleCommand {

  private static final String RETURN = "1";

  public PenDown(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    for(Turtle activeTurtle: activatedTurtles) {
      activeTurtle.penDown();
      commandNode.setResult(RETURN);
    }
  }
}
