package slogo.model.Commands;


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

    int angle = getAdjustedAngle(turtle.getDegree());

    //have to fix these angles
    int xMultiplier = getXMultiplier(angle);
    int yMultiplier = getYMultiplier(angle);

    double angleToRadians = angleToRadians(angle);
    double rightAngle = angleToRadians(facingRight);
    int toAddX = (int) (distance * Math.sin(angleToRadians));
    int toAddY = (int) (distance * Math.sin(rightAngle - angleToRadians));

    int newX = turtle.getX() + xMultiplier * toAddX;
    int newY = turtle.getY() + yMultiplier * toAddY;

    System.out.println("\nTurtle Position: X: " + newX + " Y: " + newY);

    moveTurtleTo(newX, newY);
  }
}

//  public static void main(String[] args) {
//    ForwardCommand c = new ForwardCommand("hi", new Turtle(), 50);
//
//    Turtle t = c.getTurtle();
//    t.setDegree(40);
//
//    c.doCommand();
//
//  }

