package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Random extends MathCommand {
    public Random(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setData(((int)(Math.random() *Double.parseDouble(getParamList().get(0))))+"");
        System.out.println("Result of Random: "+ commandNode.getData());
    }
}
