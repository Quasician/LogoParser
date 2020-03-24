package slogo.model.Commands.BooleanCommands;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * LessThan command
 */
public class LessThan extends BooleanCommand {

  public LessThan(String name) {
    super(name);
  }

  /**
   * returns whether the first input is less than the second
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double test1 = Double.parseDouble(getParamList().get(0));
    double test2 = Double.parseDouble(getParamList().get(1));
    condition = test1 < test2;
    commandNode.setResult(getReturnValue(condition));
  }
}
