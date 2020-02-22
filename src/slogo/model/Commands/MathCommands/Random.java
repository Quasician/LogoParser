package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;

public class Random extends MathCommand {
    public Random(String name) {
        super(1, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack((int)(Math.random() * values[0]));
    }
}
