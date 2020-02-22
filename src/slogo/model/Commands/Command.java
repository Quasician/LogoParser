package slogo.model.Commands;

import slogo.model.CommandStack;
import slogo.model.Turtle;

import java.util.ArrayList;

//merge
public abstract class Command {
    protected int params;
    protected Turtle turtle;
    protected String name;
    protected int[] values;

    protected CommandStack commandStack;

    public Command(int params, String name)
    {
        this.name = name;
        this.params = params;
        values = new int[params];
    }
    public abstract void doCommand();

    public int getParamNumber()
    {
        return params;
    }
    public int[] getParamList()
    {
        return values;
    }
    public void setCommandStack(CommandStack commandStack)
    {
        this.commandStack = commandStack;
    }
    public void setTurtle(Turtle turtle)
    {
        this.turtle = turtle;
    }
}
