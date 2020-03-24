package slogo.model.Commands.DisplayCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * SetShape command
 */
public class SetShape extends DisplayCommand {

  public SetShape(String name) {
    super(name);
  }

  /**
   * Sets the turtle image to the chosen index if in bounds,
   * else throws error
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));
    int maxIndex = displayOption.getNumImages();
    if (index < 0 || index >= maxIndex) {
      throw new CommandException(String.format(errors.getString("ImageIndex"), maxIndex-1));
    }
    displayOption.setImageIndex(index);
    commandNode.setResult(index + "");
  }
}
