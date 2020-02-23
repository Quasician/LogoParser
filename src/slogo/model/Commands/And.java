package slogo.model.Commands;

public class And extends BooleanCommand {

  private static final int NUM_PARAMS = 2;

  public And(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    int test1 = values[1];
    int test2 = values[0];
    condition = (test1 != 0 && test2 != 0);
    commandStack.pushOntoValueStack(getReturnValue(condition));
  }
}
