package slogo.model.Commands.MultipleTurtleCommands;

import java.util.List;
import slogo.model.CommandParser;
import slogo.model.TreeNode;

public class Ask extends MultipleTurtleCommand {

  public Ask(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    String list = getParamList().get(0).replaceFirst("\\[", "").replaceFirst("\\]", "");
    list = list.trim();
    String[] turtlesToRun = list.split("\\s+");
    String[] commands = getCommandsArray();
    String allCommands = getAllCommands();
    String finalValue = "";

    List<Boolean> currentTurtleStates = getCurrentTurtleStates();
    for (String activatedTurtle : turtlesToRun) {
      CommandParser miniparser = new CommandParser(activatedTurtles, variables, language, customCommandStorage);
      String parsed = miniparser.miniParse(activatedTurtle);
      miniparser.setTurtles(generate1ActiveTurtleList((int)Double.parseDouble(parsed)));
      finalValue = miniparser.miniParse(allCommands);
    }
    setResult(currentTurtleStates, commandNode, commands, finalValue);
  }
}
