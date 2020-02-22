package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;

public class Minus extends MathCommand {
    public Minus(String name) {
        super(1, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(-1*values[0]);
    }
}
