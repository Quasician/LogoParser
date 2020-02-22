package slogo.model.Commands;

public class Remainder extends MathCommand {
    public Remainder(String name) {
        super(2, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(values[0]%values[1]);
    }
}
