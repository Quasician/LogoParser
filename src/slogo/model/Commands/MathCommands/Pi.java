package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Pi extends MathCommand {
    public Pi(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(Math.PI+"");
        System.out.println("Result of Pi: "+ commandNode.getResult());
    }
}
