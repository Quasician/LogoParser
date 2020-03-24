package slogo.model.Commands.DisplayCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * SetPalette command
 */
public class SetPalette extends DisplayCommand {

  private static final int RGB_SIZE = 3;
  private static final int MAX_RGB = 255;
  private static final int MIN_RGB = 0;

  public SetPalette(String name) {
    super(name);
  }

  /**
   * Sets the color of the palette at the input index
   * If the index is negative, throws exception
   * If the index is greater than the current largest index, adds a new color to the end
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (index < 0) {
      throw new CommandException(String.format(errors.getString(INDEX_ERROR),  displayOption.getLargestIndex()));
    }
    if (index > displayOption.getLargestIndex())
      index = displayOption.getLargestIndex() + 1;

    int[] rgb = new int[RGB_SIZE];
    for (int i = 0; i < RGB_SIZE; i++) {
      rgb[i] = Integer.parseInt(getParamList().get(i+1));
      if (rgb[i] > MAX_RGB || rgb[i] < MIN_RGB)
        throw new CommandException(errors.getString(RGB_ERROR));
    }

    displayOption.setColorAt(index, rgb);
    commandNode.setResult(index + "");
  }
}
