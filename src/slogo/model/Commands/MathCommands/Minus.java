package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Minus extends MathCommand {
    public Minus(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(-1*Double.parseDouble(getParamList().get(0))+"");
        System.out.println("Result of Minus: "+commandNode.getResult());
    }
}
