package slogo.model.Commands;

public class Minus extends MathCommand {
    public Minus(String name) {
        super(1, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(-1*values[0]);
    }
}
