package slogo.model.Commands;

public class Quotient extends MathCommand {
    public Quotient(String name) {
        super(2, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(values[0]/values[1]);
    }
}
