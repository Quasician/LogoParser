package slogo.model.Commands.BooleanCommands;

import slogo.model.Commands.BooleanCommands.BooleanCommand;
import slogo.model.TreeNode;

public class Not extends BooleanCommand {

  public Not(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double test = Double.parseDouble(getParamList().get(0));
    condition = test == 0;
    commandNode.setData(getReturnValue(condition));
  }

}
