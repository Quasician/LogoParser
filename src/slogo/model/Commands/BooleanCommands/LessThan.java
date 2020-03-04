package slogo.model.Commands.BooleanCommands;
import slogo.model.TreeNode;

public class LessThan extends BooleanCommand {

  public LessThan(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = test1 < test2;
    commandNode.setResult(getReturnValue(condition));
  }
}
