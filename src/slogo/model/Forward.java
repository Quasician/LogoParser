package slogo.model;

public class Forward implements Command {
  private int distance;
  private String name;
  private Turtle turtle;

  public Forward(String name) {
    this.name = name;
  }

  public Forward(String name, Turtle turtle) {
    this.name = name;
    this.turtle = turtle;
  }

  public Forward(String name, Turtle turtle, int distance) {
    this.name = name;
    this.turtle = turtle;
    this.distance = distance;
  }

  private double angleToRadians(int angle) {
    return angle * Math.PI / 180;
  }

  @Override
  public void doCommand() {
    int angle = turtle.getDegree();
    if (angle > 180)
      angle = 360 - angle;

    double angleToRadians = angleToRadians(angle);
    double rightAngle = angleToRadians(90);
    int newX = turtle.getX();
    int newY = turtle.getY();

    newX += (int) (distance * Math.sin(angleToRadians));
    newY += (int) (distance * Math.sin(rightAngle - angleToRadians));

    System.out.println(newX);
    System.out.println(newY);

    turtle.setX(newX);
    turtle.setY(newY);
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
}
