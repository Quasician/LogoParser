package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Minus extends MathCommand {
    public Minus(String name) {
        super(1, name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setData(-1*Double.parseDouble(getParamList().get(0))+"");
        System.out.println("Result of Minus: "+commandNode.getData());
    }
}
