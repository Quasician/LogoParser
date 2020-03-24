package slogo.model.Commands.BooleanCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * GreaterThan command
 */
public class GreaterThan extends BooleanCommand {
  public GreaterThan(String name) {
    super(name);
  }

  /**
   * Returns whether the first value is greater than the second
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = test1 > test2;
    commandNode.setResult(getReturnValue(condition));
  }
}
