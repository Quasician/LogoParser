package slogo.model.Commands;

public class SetXY extends TurtleCommand {
  private static final int NUM_PARAMS = 2;
  private int x;
  private int y;

  public SetXY(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    x = values[0];
    y = values[1];

    commandStack.pushOntoValueStack((int)distanceFormula(turtle.getX(), turtle.getY(), x, y));
    moveTurtleTo(x, y);
  }
}
