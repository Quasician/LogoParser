package slogo.model.Commands;

public class Pi extends MathCommand{
    public Pi(String name) {
        super(1, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack((int)Math.PI);
    }
}
