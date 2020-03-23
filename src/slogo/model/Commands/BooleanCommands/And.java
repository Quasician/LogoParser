package slogo.model.Commands.BooleanCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 *
 */
public class And extends BooleanCommand {

  public And(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = (test1 != 0 && test2 != 0);
    commandNode.setResult(getReturnValue(condition));
  }
}
