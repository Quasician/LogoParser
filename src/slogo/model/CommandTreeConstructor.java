package slogo.model;


import javafx.util.Pair;
import slogo.model.Commands.VCUCommands.CustomCommand;

import java.util.*;
import java.util.regex.Pattern;

public class CommandTreeConstructor {

  private static final GeneralParserBehavior BEHAVIOR = new GeneralParserBehavior();
  private static final String MAKE_USER_INSTRUCTION = "MakeUserInstruction";
  private HashMap<Pattern, String> translations;
  private CustomCommandStorage customCommandStorage;
  private static final String RESOURCES_PACKAGE = BEHAVIOR.getResourcesString();
  private static ResourceBundle commandParameterNumbers = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ParameterNumbers");
  private ResourceBundle errors = BEHAVIOR.getErrorBundle();

  public CommandTreeConstructor(HashMap<Pattern, String> translations, CustomCommandStorage customCommandStorage) {
    this.translations = translations;
    this.customCommandStorage = customCommandStorage;
  }

  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }

  private List<TreeNode> buildSubtree(List<String> commandElements) {
    ArrayList<TreeNode> answer = new ArrayList<>();
    TreeNode head = buildList(commandElements);
    while (head != null) {
      TreeNode root = new TreeNode();
      head = createSubTree(root, head);
      if (root.getChildren().size() > 0) {
        answer.add(root.getChildren().get(0));
      }
    }
    return answer;
  }

  //Throws error if there are extra doubles in commands (like fd 50 50).
  private void checkParameters(List<TreeNode> nodes) {
    for (TreeNode node : nodes) {
      if (node.getChildren().size() == 0) {
        try {
          double value = Double.parseDouble(node.getResult());
          throw new CommandException(errors.getString("WrongParameterNumber"));
        } catch (NumberFormatException e) {
          //do nothing
        }
      }
    }
  }

  public List<TreeNode> buildTrees(String commands) {
    ArrayList<String> commandElements = new ArrayList<>(
        Arrays.asList(commands.split("(\\n|\\s)+|(\\s|\\n)+")));
    System.out.println("COMMAND LIST: " + commandElements.toString());
    List<TreeNode> answer = buildSubtree(commandElements);
    System.out.println("SIZE OF FINAL LIST: " + answer.size());
    checkParameters(answer);
    return answer;
  }

  private TreeNode buildList(List<String> commandElements) {
    TreeNode head = new TreeNode();
    TreeNode next = head;
    boolean isInComment = false;

    for (String element : commandElements) {
      // needs to be refactored
      if (element.equals("#")|| element.equals("|n")) {
        isInComment = checkIfInComment(element);
        continue;
      }
      if (!element.equals("") && !isInComment) {
        TreeNode node = new TreeNode(element);
        next.addChild(node);
        next = next.getChildren().get(0);
      }
    }
    return head.getChildren().get(0);
  }

  private boolean checkIfInComment(String element) {
    if(element.equals("#")) {
      return true;
    }
    return false;
  }


  private TreeNode createSubTree(TreeNode buildingNode, TreeNode commandNode) {
    if (commandNode == null) {
      return commandNode;
    }
    String currentCommand = commandNode.getName();
    commandNode = iterateCommandNode(commandNode);
    if (currentCommand.equals("MakeUserInstruction") || match(currentCommand, BEHAVIOR.getCommandPattern())) {
      return handleCommands(buildingNode, commandNode, currentCommand);
    }
    else if (match(currentCommand, BEHAVIOR.getVariablePattern()) || match(currentCommand, BEHAVIOR.getConstantPattern())) {
      addNewTreeNodeToBuildingNode(currentCommand, buildingNode);
      return commandNode;
    } else if (currentCommand.equals("[")) {
      Pair<String, TreeNode> result = joinList("[ ", commandNode, 1);
      addNewTreeNodeToBuildingNode(result.getKey(), buildingNode);
      return result.getValue();
    }
    return null;
  }

  private void addNewTreeNodeToBuildingNode(String value, TreeNode buildingNode) {
    TreeNode node = new TreeNode(value);
    node.setResult(value);
    buildingNode.addChild(node);
  }

  private TreeNode iterateCommandNode(TreeNode commandNode) {
    if (commandNode.getChildren().size() == 0) {
      return null;
    }
    return commandNode.getChildren().get(0);
  }

  private int getParameterNumber(String currentElement) {
    int parameterNumber = 0;
    if (customCommandStorage.isACustomCommand(currentElement)) {
      parameterNumber = customCommandStorage.getCustomCommandParamNumber(currentElement);
    } else {
      try {
        parameterNumber = Integer
            .parseInt(commandParameterNumbers.getString(currentElement));
      } catch (MissingResourceException e) {
        String errorMessage = String.format(errors.getString("WrongParameter"), currentElement);
        //throw new CommandException(errorMessage);
      }
    }
    return parameterNumber;
  }

  private TreeNode handleCommands(TreeNode buildingNode, TreeNode commandNode,
      String currentElement) {
    System.out.println("CURRENT ELEMENT: " + currentElement);
    int parameterNumber = getParameterNumber(currentElement);
    TreeNode head = new TreeNode(currentElement);
    head.setResult(currentElement);
    buildingNode.addChild(head);
    if (currentElement.equals(MAKE_USER_INSTRUCTION)) {
      head.addChild(commandNode);
      commandNode.setResult(commandNode.getName());
      commandNode = commandNode.getChildren().get(0);
    }
    for (int i = 0; i < parameterNumber; i++) {
      commandNode = createSubTree(head, commandNode);
    }
    return commandNode;
  }

  private Pair joinList(String currentList, TreeNode commandNode, int numOpen) {
    System.out.println("NUM OPEN: " + numOpen);
    if (commandNode == null) {
      return new Pair(currentList + " " + commandNode.getName(), null);
    }
    else if (commandNode.getChildren().size() <= 0) {
      return new Pair(currentList + " " + commandNode.getName(), null);
    }
    else if (commandNode.getName().equals("]") && numOpen == 1 ) {
      return new Pair(currentList + " " + commandNode.getName(), commandNode.getChildren().get(0));
    } else if (commandNode.getName().equals("]") && numOpen != 1) {
      return joinList(currentList + " " + commandNode.getName(), commandNode.getChildren().get(0), numOpen - 1);
    } else if (commandNode.getName().equals("[")) {
      return joinList(currentList + " " + commandNode.getName(), commandNode.getChildren().get(0), numOpen + 1);
    }
    return joinList(currentList + " " + commandNode.getName(), commandNode.getChildren().get(0), numOpen);
  }
}
