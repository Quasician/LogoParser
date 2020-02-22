package slogo.model.Commands;

public class Product extends MathCommand{
    public Product(String name) {
        super(2, name);
    }
    @Override
    public void doCommand() {
        commandStack.pushOntoValueStack(values[0]*values[1]);
    }
}
