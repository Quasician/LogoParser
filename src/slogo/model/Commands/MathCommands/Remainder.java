package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;

public class Remainder extends MathCommand {
    public Remainder(String name) {
        super(2, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(values[0]%values[1]);
    }
}
