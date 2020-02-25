package slogo.model;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactory;
import slogo.model.Commands.CommandFactoryInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandTreeConstructor {

    private Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
    private Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
    private Pattern variablePattern = Pattern.compile(":[a-zA-Z_]+");
    private Pattern commentPattern = Pattern.compile("^#.*");
    private Pattern newLinePattern = Pattern.compile("\n");

    private static final String RESOURCES_PACKAGE =
        "resources.";

    public static ResourceBundle commandParameterNumbers = ResourceBundle
        .getBundle(RESOURCES_PACKAGE + "ParameterNumbers");

    public CommandTreeConstructor(String commands)
    {}

    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    public List<TreeNode> buildTrees(String commands){
        ArrayList<String> commandElements = new ArrayList<>(Arrays.asList(commands.split("(\\n|\\s)+|(\\s|\\n)+")));
        System.out.println("COMMAND LIST: " +commandElements.toString());
        ArrayList<TreeNode> answer = new ArrayList<>();
        ListNode head = buildList(commandElements);
        while(head != null){
            TreeNode root = new TreeNode();
            System.out.println("HEAD:" + head.getData());
            head = createSubTree(root, head);
            answer.add(root.getChildren().get(0));
        }
        return answer;
    }

    private ListNode buildList(List<String> commandElements){
        ListNode head = new ListNode();
        ListNode next = head;
        boolean isInComment = false;

        for(String element: commandElements){
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
                ListNode node = new ListNode(element);
                next.addChild(node);
                System.out.println("NODE:" + node.getData());
                next = next.getChild();
            }
        }

        return head.getChild();
    }


    private ListNode createSubTree(slogo.model.Node buildingNode, ListNode commandNode){
        if(commandNode == null){
            return commandNode;
        }
        String currentCommand = commandNode.getData();
        commandNode = commandNode.getChild();
        if(match(currentCommand, commandPattern)){
            return handleCommands(buildingNode, commandNode, currentCommand);
        }
        // needs to also check for variables (use or statement
        else if(match(currentCommand, constantPattern)|| match(currentCommand, variablePattern )){
            buildingNode.addChild(new TreeNode(currentCommand));
            return commandNode;
        }
//        else if(match(currentCommand, commentPattern)|| match(currentCommand, newLinePattern)){
//            System.out.println("YEET: "+ commandNode.getData());
//            commandNode.addChild(null);
//            return null;
//        }
        return null;
    }

    //trying to do error checking
    private ListNode handleCommands(Node buildingNode, ListNode commandNode, String currentElement){
//      if (!CommandParamNumberHashMap.mapContains(currentElement)) {
//          System.out.println("here no match");
//          EventQueue.invokeLater(new Runnable() {
//              @Override
//              public void run() {
//                  JOptionPane.showMessageDialog(null, "This is invalid command");
//              }
//          });
//      }

        int parameterNumber = Integer.parseInt(commandParameterNumbers.getString(currentElement));
        //int parameterNumber = CommandParamNumberHashMap.getCommandParamNumber(currentElement);

        TreeNode head = new TreeNode(currentElement);
        buildingNode.addChild(head);
        for(int i = 0; i < parameterNumber; i++){
            commandNode = createSubTree(head,commandNode);
        }
        return commandNode;
    }
}
