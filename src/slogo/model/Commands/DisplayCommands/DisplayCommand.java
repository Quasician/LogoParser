package slogo.model.Commands.DisplayCommands;

import slogo.model.ColorOptions;
import slogo.model.CommandException;
import slogo.model.Commands.Command;

public abstract class DisplayCommand extends Command {

  private static final String INDEX_ERROR = "Index";

  public DisplayCommand(String name) {
    super(name);
  }

  protected boolean indexInBounds(int index) {
    return index <= ColorOptions.getLargestIndex() && index >= 0;
  }

  protected void indexError() {
    String errorMessage = String.format(errors.getString(INDEX_ERROR), ColorOptions.getLargestIndex());
    throw new CommandException(errorMessage);
  }

}
