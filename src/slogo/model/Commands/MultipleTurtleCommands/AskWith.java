package slogo.model.Commands.MultipleTurtleCommands;

import java.util.List;
import slogo.model.CommandParser;
import slogo.model.TreeNode;
import slogo.model.Turtle;

public class AskWith extends MultipleTurtleCommand {

  public AskWith(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    String condition = getCondition();
    String conditionResult = "";
    String[] commands = getCommandsArray();
    String allCommands = getAllCommands();
    String finalValue = "";

    List<Boolean> currentTurtleStates = getCurrentTurtleStates();

    CommandParser miniparser = new CommandParser(activatedTurtles,variables, language, customCommandStorage);
    for(Turtle turtle:turtles) {
        miniparser.setTurtles(generate1ActiveTurtleList(turtle.getId()-1));
        conditionResult = miniparser.miniParse(condition);
        if(conditionResult.equals("1")) {
            finalValue = miniparser.miniParse(allCommands);
        }
    }

    setResult(currentTurtleStates, commandNode, commands, finalValue);
    }
  }

