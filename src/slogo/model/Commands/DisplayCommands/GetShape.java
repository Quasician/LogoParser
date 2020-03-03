package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

public class GetShape extends DisplayCommand {

  public GetShape(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    int currentImage = displayOption.getImageIndex().get();

    commandNode.setResult(currentImage + "");
  }
}
