package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

public class SetBackground extends DisplayCommand {

  public SetBackground(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));
    //check to make sure the index
    //is within the possible options
    //set background to the color represented by index
  }
}
