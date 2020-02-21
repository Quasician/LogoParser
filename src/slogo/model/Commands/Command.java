package slogo.model.Commands;

import java.util.ArrayList;

public abstract class Command {
    protected int params;
    protected int[] values;
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
}
