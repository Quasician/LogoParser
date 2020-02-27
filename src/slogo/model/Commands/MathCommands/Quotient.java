package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Quotient extends MathCommand {
    public Quotient(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(Double.parseDouble(getParamList().get(0))/Double.parseDouble(getParamList().get(1))+"");
        System.out.println("Result of Quotient: "+ commandNode.getResult());
    }
}
