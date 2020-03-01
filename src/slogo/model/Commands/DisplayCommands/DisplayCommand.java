package slogo.model.Commands.DisplayCommands;

import slogo.model.ColorOptions;
import slogo.model.Commands.Command;

public abstract class DisplayCommand extends Command {

  public DisplayCommand(String name) {
    super(name);
  }

  protected boolean indexInBounds(int index) {
    return index <= ColorOptions.getLargestIndex() && index >= 0;
  }

}
