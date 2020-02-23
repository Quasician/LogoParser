package slogo.model.Commands;

public abstract class BooleanCommand extends Command {
  protected static final int TRUE = 1;
  protected static final int FALSE = 0;
  protected boolean condition;

  public BooleanCommand(int params, String name) {
    super(params, name);
  }

  protected int getReturnValue(boolean condition) {
    int ret = FALSE;
    if (condition)
      ret = TRUE;
    return ret;
  }
}
