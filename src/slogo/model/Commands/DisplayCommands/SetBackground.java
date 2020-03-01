package slogo.model.Commands.DisplayCommands;

import slogo.model.ColorOptions;
import slogo.model.TreeNode;

public class SetBackground extends DisplayCommand {

  public SetBackground(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    if (index <= ColorOptions.getLargestIndex() && index >= 0) {
      ColorOptions.setCurrentBackground(index);
      commandNode.setResult("" + index);
    } else {
      throw new RuntimeException("The index is invalid");
    }

    //check to make sure the index
    //is within the possible options
    //set background to the color represented by index
  }
}
