package slogo.model.Commands.MathCommands;

import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Power extends MathCommand {
    public Power(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setData(Math.pow(Double.parseDouble(getParamList().get(0)),Double.parseDouble(getParamList().get(1)))+"");
        System.out.println("Result of Power: "+ commandNode.getData());
    }
}
