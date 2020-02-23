package slogo.model.Commands;

public class Not extends BooleanCommand {

  private static final int NUM_PARAMS = 1;

  public Not(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    int test = values[0];
    condition = test == 0;
    commandStack.pushOntoValueStack(getReturnValue(condition));
  }
}
