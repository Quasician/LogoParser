package slogo.model.Commands;


import slogo.model.CommandStack;
import slogo.model.Turtle;

import java.util.ArrayList;

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

    moveTurtle(forward, distance);
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

