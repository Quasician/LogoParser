package slogo.model.Commands.DisplayCommands;

import slogo.model.UIOptions;
import slogo.model.TreeNode;

public class SetPenColor extends DisplayCommand {

  public SetPenColor(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (indexInBounds(index)) {
      UIOptions.setCurrentChoicePen(index);
      commandNode.setResult("" + index);
    } else {
      indexError();
    }
  }
}
