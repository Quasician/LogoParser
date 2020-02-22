package slogo.model.Commands;

public class PenUp extends TurtleCommand {

  public PenUp(String name) {
    super(0, name);
  }

  @Override
  public void doCommand() {
    turtle.penUp();
    commandStack.pushOntoValueStack(0);
  }
}
