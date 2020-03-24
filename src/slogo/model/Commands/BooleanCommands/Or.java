package slogo.model.Commands.BooleanCommands;

import slogo.model.Commands.BooleanCommands.BooleanCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Or command
 */
public class Or extends BooleanCommand {

  public Or(String name) {
    super(name);
  }

  /**
   * Returns true if either of the input numbers are nonzero.
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = (test1 != 0 || test2 != 0);
    commandNode.setResult(getReturnValue(condition));
  }
}
