package slogo.model.Commands.BooleanCommands;

import slogo.model.Commands.Command;

/**
 * @author Sanna
 *
 * Abstract class that provides general behavior for all boolean commands.
 * Assumption for all boolean commands: the input parameters must be numerical.
 * Error checking for this should be done before reaching the command
 */
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
