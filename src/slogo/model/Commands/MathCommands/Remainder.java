package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Remainder extends MathCommand {
    public Remainder(String name) {
        super(2, name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setData(Double.parseDouble(getParamList().get(0))%Double.parseDouble(getParamList().get(1))+"");
        System.out.println("Result of Remainder"+ commandNode.getData());
    }
}
