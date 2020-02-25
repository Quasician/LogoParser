package slogo.model.Commands.MathCommands;
import slogo.model.TreeNode;

public class Cosine extends MathCommand {
    public Cosine(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(Math.toDegrees(Math.cos(Double.parseDouble(getParamList().get(0))))+"");
        System.out.println("Result of Cosine: "+ commandNode.getResult());
    }
}
