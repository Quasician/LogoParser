package slogo.model.Commands;

public class Right extends TurtleCommand {

  private static final int NUM_PARAMS = 1;
  private int degrees;

  public Right(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    commandStack.pushOntoValueStack(values[0]);
    degrees = values[0];

    rotateTurtle(1, degrees);
  }
}
