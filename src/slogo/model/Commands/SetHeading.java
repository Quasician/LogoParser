package slogo.model.Commands;

public class SetHeading extends TurtleCommand {

  private static final int NUM_PARAMS = 1;
  private int degrees;

  public SetHeading(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    degrees = values[0];

    int returnDegrees = degrees - turtle.getDegree(); //value that needs to be sent back
    turtle.setDegree(degrees % 360);

//    System.out.println(turtle.getDegree());
//    System.out.println(returnDegrees);
    commandStack.pushOntoValueStack(returnDegrees);
  }
}
