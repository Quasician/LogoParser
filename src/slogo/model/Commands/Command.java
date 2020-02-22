package slogo.model.Commands;

import slogo.model.CommandStack;

import java.util.ArrayList;

public abstract class Command {
    protected int params;
    protected int[] values;
    protected CommandStack commandStack;
    public Command(int params)
    {
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
}
