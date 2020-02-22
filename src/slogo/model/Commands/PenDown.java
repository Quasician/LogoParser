package slogo.model.Commands;

public class PenDown extends TurtleCommand {

  public PenDown(String name) {
    super(0, name);
  }

  @Override
  public void doCommand() {
    turtle.penDown();
    commandStack.pushOntoValueStack(1);
  }
}
