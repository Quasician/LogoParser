package slogo.model.Commands;

public class HideTurtle extends TurtleCommand {

  public HideTurtle(String name) {
    super(0, name);
  }

  @Override
  public void doCommand() {
    turtle.hide();

    commandStack.pushOntoValueStack(0);
  }
}