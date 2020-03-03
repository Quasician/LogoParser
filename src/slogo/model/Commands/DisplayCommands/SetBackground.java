package slogo.model.Commands.DisplayCommands;

import slogo.model.UIOption;
import slogo.model.TreeNode;

public class SetBackground extends DisplayCommand {

  public SetBackground(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (indexInBounds(index)) {
     // UIOption.setCurrentBackground(index);
      displayOption.setCurrentBackground(index);
      commandNode.setResult("" + index);
    } else {
      indexError();
    }
  }
}
