package slogo.model;

import javafx.util.Pair;

import java.awt.desktop.SystemSleepEvent;
import java.util.*;
import java.util.regex.Pattern;

public class CommandTreeConstructor {
    private Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
    private Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
    private Pattern variablePattern = Pattern.compile(":[a-zA-Z_]+");
    private Pattern commentPattern = Pattern.compile("^#.*");
    private Pattern newLinePattern = Pattern.compile("\n");
    private HashMap<Pattern, String> translations;


    public CommandTreeConstructor(HashMap<Pattern, String> translations)
    {
        this.translations = translations;
    }

    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    public List<TreeNode> buildTrees(String commands){
        ArrayList<String> commandElements = new ArrayList<>(Arrays.asList(commands.split("(\\n|\\s)+|(\\s|\\n)+")));
        System.out.println("COMMAND LIST: " +commandElements.toString());
        ArrayList<TreeNode> answer = new ArrayList<>();
        TreeNode head = buildList(commandElements);
        while(head != null){
            TreeNode root = new TreeNode();
            System.out.println("HEAD:" + head.getName());
            head = createSubTree(root, head);
            answer.add(root.getChildren().get(0));
        }
        return answer;
    }

    private TreeNode buildList(List<String> commandElements){
        TreeNode head = new TreeNode();
        TreeNode next = head;
        boolean isInComment = false;

        for(String element: commandElements){
            // needs to be refactored
            if(element.equals("#"))
            {
                isInComment = true;
                continue;
            }
            if(element.equals("|n"))
            {
                isInComment = false;
                continue;
            }
            if(!element.equals("") && !isInComment){
                TreeNode node = new TreeNode(element);
                next.addChild(node);
                System.out.println(next.getName() +  " is now a parent of " + node.getName());
                next = next.getChildren().get(0);
            }
        }

        return head.getChildren().get(0);
    }


    private TreeNode createSubTree(TreeNode buildingNode, TreeNode commandNode){
        if(commandNode == null ){
            return commandNode;
        }
        String currentCommand = commandNode.getName();
        if(commandNode.getChildren().size() == 0)
        {
            commandNode = null;
        }else
        {
            commandNode = commandNode.getChildren().get(0);
        }
        if(match(currentCommand, commandPattern)){
            return handleCommands(buildingNode, commandNode, currentCommand);
        }
        // needs to also check for variables (use or statement
        else if(match(currentCommand, variablePattern )){
            buildingNode.addChild(new TreeNode(currentCommand));
            return commandNode;
        }
        else if(match(currentCommand, constantPattern)){
            TreeNode node = new TreeNode(currentCommand);
            node.setResult(currentCommand);
            buildingNode.addChild(node);
            return commandNode;
        }
        else if(currentCommand.equals("[")){
            Pair<String, TreeNode> result = joinList("", commandNode);
            TreeNode node = new TreeNode(result.getKey());
            node.setResult(result.getKey());
            buildingNode.addChild(node);
            System.out.println(result.getKey());
            return result.getValue();
        }
//        else if(match(currentCommand, commentPattern)|| match(currentCommand, newLinePattern)){
//            System.out.println("YEET: "+ commandNode.getName());
//            commandNode.addChild(null);
//            return null;
//        }
        return null;
    }

    private TreeNode handleCommands(TreeNode buildingNode, TreeNode commandNode, String currentElement){
        int parameterNumber = CommandParamNumberHashMap.getCommandParamNumber(getSymbol(currentElement));
        //System.out.println("Param number: " +parameterNumber);
        TreeNode head = new TreeNode(currentElement);
        buildingNode.addChild(head);
        for(int i = 0; i < parameterNumber; i++){
            commandNode = createSubTree(head,commandNode);
        }
        return commandNode;
    }

    private Pair joinList(String currentList, TreeNode commandNode){
        //System.out.println("Current List: " + curList);
        if(commandNode == null)
        {
            System.out.println("Error");
            return null;
        }
        else if(commandNode.getName().equals("]"))
        {
            if(commandNode.getChildren().size()==1)
            {
                return new Pair(currentList, commandNode.getChildren().get(0));
            }
            return new Pair(currentList, null);
        }

        return joinList(currentList+ " "+ commandNode.getName(), commandNode.getChildren().get(0));
    }

    private String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Map.Entry<Pattern, String> e : translations.entrySet()) {
            if (match(text, e.getKey())) {
                //System.out.println(e.getKey());
                return e.getValue();
            }
        }
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }
}
