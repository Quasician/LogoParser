package slogo.model.Commands.BooleanCommands;

import slogo.model.Commands.Command;

public abstract class BooleanCommand extends Command {
  protected static final String TRUE = "1";
  protected static final String FALSE = "0";
  protected boolean condition;

  public BooleanCommand(String name) {
    super(name);
  }

  protected String getReturnValue(boolean condition) {
    String ret = FALSE;
    if (condition)
      ret = TRUE;
    return ret;
  }
}
