package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * GetPenColor command
 */
public class GetPenColor extends DisplayCommand {

  public GetPenColor(String name) {
    super(name);
  }

  /**
   * Return the current index of the pen color
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setResult(displayOption.getPenIndex() + "");
  }
}
