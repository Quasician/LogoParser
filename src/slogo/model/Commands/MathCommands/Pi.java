package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;

public class Pi extends MathCommand {
    public Pi(String name) {
        super(1, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack((int)Math.PI);
    }
}
