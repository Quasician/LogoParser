package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * SetBackground command
 */
public class SetBackground extends DisplayCommand {

  public SetBackground(String name) {
    super(name);
  }

  /**
   * Sets the background color to the chosen index if valid, otherwise throws error
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (indexInBounds(index)) {
      displayOption.setCurrentBackground(index);
      commandNode.setResult("" + index);
    } else {
      indexError();
    }
  }
}
