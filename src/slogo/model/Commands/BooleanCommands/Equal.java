package slogo.model.Commands.BooleanCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Equal command
 */
public class Equal extends BooleanCommand {

  public Equal(String name) {
    super(name);
  }

  /**
   * Returns 1 if the two numbers are equal, 0 else
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = test1 == test2;
    commandNode.setResult(getReturnValue(condition));
  }
}
