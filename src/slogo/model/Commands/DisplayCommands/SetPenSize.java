package slogo.model.Commands.DisplayCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;
import slogo.model.UIOption;

public class SetPenSize extends DisplayCommand {

  public SetPenSize(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    double penSize = Double.parseDouble(getParamList().get(0));

    if (penSize <= 0)
      throw new CommandException(errors.getString("Negative"));
    else {
      UIOption.setPenWidth(penSize);
      commandNode.setResult("" + penSize);
    }
  }
}
