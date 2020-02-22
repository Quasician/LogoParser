package slogo.model.Commands;

public class Sum extends MathCommand {
    public Sum(String name) {
        super(2, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(values[0]+values[1]);
    }
}
