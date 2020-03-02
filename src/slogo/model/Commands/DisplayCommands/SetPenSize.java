package slogo.model.Commands.DisplayCommands;

import java.awt.image.ColorConvertOp;
import slogo.model.CommandException;
import slogo.model.TreeNode;
import slogo.model.UIOptions;

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
      UIOptions.setPenWidth(penSize);
      commandNode.setResult("" + penSize);
    }
  }
}
