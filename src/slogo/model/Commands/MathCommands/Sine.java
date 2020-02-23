package slogo.model.Commands.MathCommands;
import slogo.model.TreeNode;

public class Sine extends MathCommand {
    public Sine(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setData(Math.toDegrees(Math.sin(Double.parseDouble(getParamList().get(0))))+"");
        System.out.println("Result of Sine: "+ commandNode.getData());
    }
}
