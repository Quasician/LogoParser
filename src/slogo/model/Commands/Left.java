package slogo.model.Commands;

import slogo.model.Turtle;

public class Left extends TurtleCommand {

  private static final int NUM_PARAMS = 1;
  private int degrees;

  public Left(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    commandStack.pushOntoValueStack(values[0]);
    degrees = values[0];

    rotateTurtle(-1, degrees);
  }

  //below are methods for testing
//
//  public Turtle getTurtle() {
//    return turtle;
//  }
//
//  public static void main(String[] args) {
//    Left l = new Left("left");
//    l.setTurtle(new Turtle());
//
//    l.doCommand();
//    System.out.println("Turtle degrees is now: " + l.getTurtle().getDegree());
//  }
}
