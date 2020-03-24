package slogo.model.Commands.BooleanCommands;

import slogo.model.Commands.BooleanCommands.BooleanCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Not command
 */
public class Not extends BooleanCommand {

  public Not(String name) {
    super(name);
  }

  /**
   * Returns the not of the input value
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double test = Double.parseDouble(getParamList().get(0));
    condition = test == 0;
    commandNode.setResult(getReturnValue(condition));
  }

}
