package slogo.model.Commands.MathCommands;

import com.sun.source.tree.Tree;
import slogo.model.Commands.MathCommands.MathCommand;
import slogo.model.TreeNode;

public class Product extends MathCommand {
    public Product(String name) {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
       commandNode.setResult(Double.parseDouble(getParamList().get(0))*Double.parseDouble(getParamList().get(1))+"");
       System.out.println("Result of Product: "+ commandNode.getResult());
    }
}
