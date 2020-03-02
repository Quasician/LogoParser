package slogo.model;


import javafx.util.Pair;
import java.util.*;
import java.util.regex.Pattern;

public class CommandTreeConstructor {

  private Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
  private Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
  private Pattern variablePattern = Pattern.compile(":[a-zA-Z_]+");
  private Pattern commentPattern = Pattern.compile("^#.*");
  private Pattern newLinePattern = Pattern.compile("\n");
  private HashMap<Pattern, String> translations;

  private static final String RESOURCES_PACKAGE =
      "resources.";

  private static ResourceBundle commandParameterNumbers = ResourceBundle
      .getBundle(RESOURCES_PACKAGE + "ParameterNumbers");

  private static final String ERRORS = RESOURCES_PACKAGE + "ErrorMessages";
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);

  public CommandTreeConstructor(HashMap<Pattern, String> translations) {
    this.translations = translations;
  }

  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }

  public List<TreeNode> buildTrees(String commands) {
    ArrayList<String> commandElements = new ArrayList<>(
        Arrays.asList(commands.split("(\\n|\\s)+|(\\s|\\n)+")));
    System.out.println("COMMAND LIST: " + commandElements.toString());
    ArrayList<TreeNode> answer = new ArrayList<>();
    TreeNode head = buildList(commandElements);
    while (head != null) {
      TreeNode root = new TreeNode();
      System.out.println("HEAD:" + head.getName());
      head = createSubTree(root, head);
      if (root.getChildren().size() > 0) {
        answer.add(root.getChildren().get(0));
      }
    }
    System.out.println("SIZE OF FINAL LIST: " + answer.size());
    for (TreeNode node : answer) {
      if (node.getChildren().size() == 0) {
        try {
          double value = Double.parseDouble(node.getResult());
          throw new CommandException(errors.getString("WrongParameterNumber"));
        } catch (NumberFormatException e) {
          //do nothing
        }
      }
    }
    return answer;
  }

  private TreeNode buildList(List<String> commandElements) {
    TreeNode head = new TreeNode();
    TreeNode next = head;
    boolean isInComment = false;

    for (String element : commandElements) {
      // needs to be refactored
      if (element.equals("#")) {
        isInComment = true;
        continue;
      }
      if (element.equals("|n")) {
        isInComment = false;
        continue;
      }
      if (!element.equals("") && !isInComment) {
        TreeNode node = new TreeNode(element);
        next.addChild(node);
        System.out.println(next.getName() + " is now a parent of " + node.getName());
        next = next.getChildren().get(0);
      }
    }

    return head.getChildren().get(0);
  }


  private TreeNode createSubTree(TreeNode buildingNode, TreeNode commandNode) {
    if (commandNode == null) {
      return commandNode;
    }
    String currentCommand = commandNode.getName();
    if (commandNode.getChildren().size() == 0) {
      commandNode = null;
    } else {
      commandNode = commandNode.getChildren().get(0);
    }
    if (currentCommand.equals("MakeUserInstruction")) {
      System.out.println("YABADABADOO");
      System.out.println(commandNode.getChildren().get(0).getName());
      return handleCommands(buildingNode, commandNode, currentCommand);
    } else if (match(currentCommand, commandPattern)) {
      return handleCommands(buildingNode, commandNode, currentCommand);
    }
    // needs to also check for variables (use or statement
    else if (match(currentCommand, variablePattern) || match(currentCommand, constantPattern)) {
      TreeNode node = new TreeNode(currentCommand);
      node.setResult(currentCommand);
      buildingNode.addChild(node);
      return commandNode;
    } else if (currentCommand.equals("[")) {
      Pair<String, TreeNode> result = joinList("[ ", commandNode, 1);
      TreeNode node = new TreeNode(result.getKey());
      node.setResult(result.getKey());
      buildingNode.addChild(node);
      System.out.println(result.getKey());
      return result.getValue();
    }
    return null;
  }

  private TreeNode handleCommands(TreeNode buildingNode, TreeNode commandNode,
      String currentElement) {
    System.out.println("CURRENT ELEMENT: " + currentElement);
    int parameterNumber = 0;
    if (CustomCommandMap.isACustomCommand(currentElement)) {
      parameterNumber = CommandParamNumberHashMap.getCommandParamNumber(currentElement);
    } else {
      try {
        parameterNumber = Integer
            .parseInt(commandParameterNumbers.getString(currentElement));
      } catch (MissingResourceException e) {
        String errorMessage = String.format(errors.getString("WrongParameter"), currentElement);
        //throw new CommandException(errorMessage);
      }
    }
    //System.out.println("Param number: " +parameterNumber);
    TreeNode head = new TreeNode(currentElement);
    head.setResult(currentElement);
    buildingNode.addChild(head);
    if (currentElement.equals("MakeUserInstruction")) {
      head.addChild(commandNode);
      commandNode.setResult(commandNode.getName());
      System.out.println("COMMAND NODE: " + commandNode.getName());
      commandNode = commandNode.getChildren().get(0);
    }
    for (int i = 0; i < parameterNumber; i++) {
      commandNode = createSubTree(head, commandNode);
    }
    return commandNode;
  }

  private Pair joinList(String currentList, TreeNode commandNode, int numOpen){
    System.out.println("NUM OPEN: " + numOpen);
    if(commandNode == null)
    {
      System.out.println("Error");
      return new Pair(currentList + " " + commandNode.getName(), null);
    }
//        else if(commandNode.getName().equals("]"))
//        {
//            if(commandNode.getChildren().size()==1 && commandNode.getChildren().get(0).getName().equals("["))
//            {
//                return new Pair(currentList + " ] ", commandNode.getChildren().get(0));
//            }
//            return new Pair(currentList+" ] ", null);
//        }
    else if(commandNode.getName().equals("]") && numOpen == 1){
      System.out.println("YEET = 1");
      if(commandNode.getChildren().size()>0)
      {
        return new Pair(currentList + " " + commandNode.getName(), commandNode.getChildren().get(0));
      }
      else
      {
        return new Pair(currentList + " " + commandNode.getName(), null);
      }
    }
    else if(commandNode.getName().equals("]") && numOpen != 1){
      System.out.println("YEET != 1");
      if(commandNode.getChildren().size()>0)
      {
        System.out.println("] -> " + numOpen);
        return joinList(currentList+ " "+ commandNode.getName(), commandNode.getChildren().get(0), numOpen-1);
      }
      else
      {
        return new Pair(currentList + " " + commandNode.getName(), null);
      }
    }
    else if(commandNode.getName().equals("[")){
      if(commandNode.getChildren().size()>0)
      {
        return joinList(currentList+ " "+ commandNode.getName(), commandNode.getChildren().get(0), numOpen+1);
      }
      else
      {
        return new Pair(currentList + " " + commandNode.getName(), null);
      }
    }
    if(commandNode.getChildren().size()>0)
    {
      return joinList(currentList+ " "+ commandNode.getName(), commandNode.getChildren().get(0), numOpen);
    }
    else
    {
      return new Pair(currentList + " " + commandNode.getName(), null);
    }
  }
}
