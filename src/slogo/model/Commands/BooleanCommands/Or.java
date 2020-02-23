package slogo.model.Commands.BooleanCommands;

import slogo.model.Commands.BooleanCommands.BooleanCommand;
import slogo.model.TreeNode;

public class Or extends BooleanCommand {

  public Or(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = (test1 != 0 || test2 != 0);
    commandNode.setData(getReturnValue(condition));
  }
}
