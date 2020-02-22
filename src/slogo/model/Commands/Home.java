package slogo.model.Commands;

public class Home extends TurtleCommand {

  public Home(String name) {
    super(0, name);
  }

  @Override
  public void doCommand() {
    int distanceTravelled = (int)distanceFormula(turtle.getX(), turtle.getY(), 0, 0);
    commandStack.pushOntoValueStack(distanceTravelled);
    
    moveTurtleTo(0, 0);
  }
}
