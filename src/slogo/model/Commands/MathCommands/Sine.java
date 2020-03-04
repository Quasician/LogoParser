package slogo.model.Commands.MathCommands;
import slogo.model.TreeNode;

public class Sine extends MathCommand {
    public Sine(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(Math.sin(Math.toRadians(Double.parseDouble(getParamList().get(0))))+"");
        System.out.println("Result of Sine: "+ commandNode.getResult());
    }
}
