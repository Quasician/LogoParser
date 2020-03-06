package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class Home extends TurtleCommand {

  public Home(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double distance = moveTurtlesTo(0,0);
    setTowards(0, 0);
    commandNode.setResult("" + distance);
  }
}
