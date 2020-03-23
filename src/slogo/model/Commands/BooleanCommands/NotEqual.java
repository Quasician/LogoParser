package slogo.model.Commands.BooleanCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * NotEqual command
 */
public class NotEqual extends BooleanCommand {

  public NotEqual(String name) {
    super(name);
  }

  /**
   * Returns true if not equal, false else
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = test1 != test2;
    commandNode.setResult(getReturnValue(condition));
  }
}
