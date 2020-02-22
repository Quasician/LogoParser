package slogo.model.Commands.TurtleCommands;


//merge
public class Forward extends TurtleCommand {

  private int distance;

  public Forward(String name) {
    super(1, name);
  }

  @Override
  public void doCommand() {
    //System.out.println("\nDID FORWARD COMMAND by "+values[0]);
    commandStack.pushOntoValueStack(values[0]);
    distance = values[0];

    int angle = turtle.getDegree();
    angle = getAdjustedAngle(angle);
    int xMultiplier = getXMultiplier(angle);
    int yMultiplier = getYMultiplier(angle);

    double angleToRadians = angleToRadians(angle);
    double rightAngle = angleToRadians(facingRight);
    int newX = turtle.getX() + xMultiplier * (int) (distance * Math.sin(angleToRadians));
    int newY = turtle.getY() + yMultiplier * (int) (distance * Math.sin(rightAngle - angleToRadians));

    System.out.println("\nTurtle Position: X: " + newX + " Y: " + newY);

    turtle.setX(newX);
    turtle.setY(newY);
  }
}

//  public Turtle getTurtle() {
//    return turtle;
//  }

//  public static void main(String[] args) {
//    ForwardCommand c = new ForwardCommand("hi", new Turtle(), 50);
//
//    Turtle t = c.getTurtle();
//    t.setDegree(40);
//
//    c.doCommand();
//
//  }

