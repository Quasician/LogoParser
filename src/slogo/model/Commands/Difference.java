package slogo.model.Commands;

public class Difference extends MathCommand{
    public Difference(String name) {
        super(2, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(values[0]-values[1]);
    }
}
