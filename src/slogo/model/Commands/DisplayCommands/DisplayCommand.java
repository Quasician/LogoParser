package slogo.model.Commands.DisplayCommands;

import slogo.model.CommandException;
import slogo.model.Commands.Command;

/**
 * @author Sanna
 *
 * This abstract class provides methods in common for all display related
 * commands.
 */
public abstract class DisplayCommand extends Command {

  protected static final String INDEX_ERROR = "Index";
  protected static final String RGB_ERROR = "RGB";

  /**
   * Constructor
   * @param name
   */
  public DisplayCommand(String name) {
    super(name);
  }

  protected boolean indexInBounds(int index) {
    return index <= displayOption.getLargestIndex() && index >= 0;
  }

  protected void indexError() {
    String errorMessage = String.format(errors.getString(INDEX_ERROR), displayOption.getLargestIndex());
    throw new CommandException(errorMessage);
  }

}
