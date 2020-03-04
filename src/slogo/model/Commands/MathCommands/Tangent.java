package slogo.model.Commands.MathCommands;
import slogo.model.TreeNode;

public class Tangent extends MathCommand {
    public Tangent(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(Math.tan(Math.toRadians(Double.parseDouble(getParamList().get(0))))+"");
        System.out.println("Result of Tangent: "+ commandNode.getResult());
    }
}
