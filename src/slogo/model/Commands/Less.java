package slogo.model.Commands;

public class Less extends BooleanCommand {

  private static final int NUM_PARAMS = 2;

  public Less(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    int expr2 = values[0];
    int expr1 = values[1];
    condition = expr1 < expr2;
    commandStack.pushOntoValueStack(getReturnValue(condition));
  }
}
