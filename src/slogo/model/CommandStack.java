package slogo.model;

import java.util.Stack;

public class CommandStack
{
    Stack<String> commandStack;
    Stack<Integer> valueStack;

    public CommandStack()
    {
        commandStack = new Stack<String>();
        valueStack = new Stack<Integer>();
    }

    public String popCommandStack(String command)
    {
        return commandStack.pop();
    }

    public int popValueStack()
    {
        return valueStack.pop();
    }

    public void pushOntoCommandStack(String command)
    {
        commandStack.push(command);
    }

    public void pushOntoValueStack(int value)
    {
        valueStack.push(value);
    }

    public boolean isCommandStackEmpty()
    {
        return commandStack.size() <= 0;
    }

    public boolean isValueStackEmpty()
    {
        return valueStack.size() <= 0;
    }
}
