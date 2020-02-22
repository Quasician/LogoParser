package slogo.model.Commands;

public class Back extends TurtleCommand {

  private int distance;

  public Back(String name) {
    super(1, name);
  }

  @Override
  public void doCommand() {
    commandStack.pushOntoValueStack(values[0]);
    distance = values[0];

    moveTurtle(backward, distance);
  }
}
