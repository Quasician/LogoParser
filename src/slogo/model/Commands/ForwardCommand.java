package slogo.model.Commands;


import slogo.model.CommandStack;
import slogo.model.Turtle;

import java.util.ArrayList;
//merge
public class ForwardCommand extends Command {
    private int distance;
    private String name;

    public ForwardCommand(String name)
    {
        super(1, name);
    }

    public ForwardCommand(String name, Turtle turtle, int distance) {
        super(1, name);
        this.name = name;
        this.turtle = turtle;
        this.distance = distance;
    }

    private double angleToRadians(int angle) {
        return angle * Math.PI / 180;
    }


    @Override
    public void doCommand() {
        //System.out.println("\nDID FORWARD COMMAND by "+values[0]);
        commandStack.pushOntoValueStack(values[0]);
        distance = values[0];

        int angle = turtle.getDegree();
        if (angle > 180)
            angle = 360 - angle;

        double angleToRadians = angleToRadians(angle);
        double rightAngle = angleToRadians(90);
        int newX = turtle.getX();
        int newY = turtle.getY();

        newX += (int) (distance * Math.sin(angleToRadians));
        newY += (int) (distance * Math.sin(rightAngle - angleToRadians));

        System.out.println("\nTurtle Position: X: "+newX + " Y: "+ newY);


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

