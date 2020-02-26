package slogo.model.Commands.MathCommands;
import slogo.model.TreeNode;

public class ArcTangent extends MathCommand {
    public ArcTangent(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(Math.toDegrees((Math.atan(Double.parseDouble(getParamList().get(0)))))+"");
        System.out.println("Result of ArcTangent: "+ commandNode.getResult());
    }
}
