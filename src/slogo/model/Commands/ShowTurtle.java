package slogo.model.Commands;

public class ShowTurtle extends TurtleCommand {

  public ShowTurtle(String name) {
    super(0, name);
  }

  @Override
  public void doCommand() {
    turtle.show();

    commandStack.pushOntoValueStack(1);
  }
}
