package slogo.model;

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
        ListNode frst = buildList(commandElements);
        while(frst != null){
            TreeNode root = new TreeNode();
            System.out.println("HEAD:" + frst.getData());
            frst = createSubTree(root, frst);
            answer.add(root.getChildren().get(0));
        }
        return answer;
    }

    private ListNode buildList(List<String> commandElements){
        ListNode head = new ListNode();
        ListNode next = head;

        for(String element: commandElements){
            if(!element.equals("")){
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
        else if(match(currentCommand, constantPattern)){
            buildingNode.addChild(new TreeNode(currentCommand));
            return commandNode;
        }
//        else if(type.equals(COMMENT_KEY) || type.equals(NEW_LINE_KEY) || type.equals(WHITESPACE_KEY)){
//            return createSubTree(buildingNode, commandNode);
//        }
        return null;
    }

    private ListNode handleCommands(Node buildingNode, ListNode commandNode, String currentElement){
        int parameterNumber = CommandParamNumberHashMap.getCommandParamNumber(currentElement);
        TreeNode head = new TreeNode(currentElement);
        buildingNode.addChild(head);
        for(int i = 0; i < parameterNumber; i++){
            commandNode = createSubTree(head,commandNode);
        }
        return commandNode;
    }
}
