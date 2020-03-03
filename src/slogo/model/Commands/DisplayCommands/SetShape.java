package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

public class SetShape extends DisplayCommand {

  public SetShape(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int index = Integer.parseInt(getParamList().get(0));

    //TODO: error checking
    displayOption.setImageIndex(index);
    commandNode.setResult(index + "");
  }
}
