package slogo.model.Commands.DisplayCommands;

import slogo.model.ColorOptions;
import slogo.model.CommandException;
import slogo.model.TreeNode;

public class SetPalette extends DisplayCommand {

  private static final int RGB_SIZE = 3;

  public SetPalette(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (index < 0) {
      throw new CommandException(String.format(errors.getString("Index"),  ColorOptions.getLargestIndex()));
    }
    if (index > ColorOptions.getLargestIndex())
      index = ColorOptions.getLargestIndex() + 1;

    int[] rgb = new int[RGB_SIZE];
    for (int i = 0; i < RGB_SIZE; i++) {
      rgb[i] = Integer.parseInt(getParamList().get(i+1));
    }

    ColorOptions.setColorAt(index, rgb);

    commandNode.setResult(index + "");
  }
}
