package slogo.model.Commands.DisplayCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * GetShape command
 */
public class GetShape extends DisplayCommand {

  public GetShape(String name) {
    super(name);
  }

  /**
   * Returns the index of the turtle's current image
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    int currentImage = displayOption.getImageIndex().get();
    commandNode.setResult(currentImage + "");
  }
}
