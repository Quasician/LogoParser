package slogo.model.Commands.MathCommands;
import slogo.model.TreeNode;

public class NaturalLog extends MathCommand {
    public NaturalLog(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setData(Math.log(Double.parseDouble(getParamList().get(0)))+"");
        System.out.println("Result of Log: "+ commandNode.getData());
    }
}
