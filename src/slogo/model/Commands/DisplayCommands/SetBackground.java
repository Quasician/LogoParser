package slogo.model.Commands.DisplayCommands;

import slogo.model.UIOptions;
import slogo.model.TreeNode;

public class SetBackground extends DisplayCommand {

  public SetBackground(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (indexInBounds(index)) {
      UIOptions.setCurrentBackground(index);
      commandNode.setResult("" + index);
    } else {
      indexError();
    }
  }
}
