package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * SetPenColor command
 */
public class SetPenColor extends DisplayCommand {

  public SetPenColor(String name) {
    super(name);
  }

  /**
   * Sets the pen color to the chosen index if in bounds, else throws error
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (indexInBounds(index)) {
      displayOption.setCurrentChoicePen(index);
      commandNode.setResult("" + index);
    } else {
      indexError();
    }
  }
}
