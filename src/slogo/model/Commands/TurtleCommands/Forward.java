package slogo.model.Commands.TurtleCommands;


import slogo.model.TreeNode;

//merge
public class Forward extends TurtleCommand {

  private double distance;

  public Forward(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    //System.out.println("\nDID FORWARD COMMAND by "+values[0]);
//    commandStack.pushOntoValueStack(values[0]);
    distance = Double.parseDouble(getParamList().get(0));
    commandNode.setData(getParamList().get(0));
    System.out.println("Result of Forward: "+commandNode.getData());

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

