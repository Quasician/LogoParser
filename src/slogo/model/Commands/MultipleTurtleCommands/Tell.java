package slogo.model.Commands.MultipleTurtleCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;
import slogo.model.Turtle;

import java.util.Arrays;

public class Tell extends MultipleTurtleCommand{
    public Tell(String name) { super(name);}

    @Override
    public void doCommand(TreeNode commandNode) {
        //DO error checking if not an int
        String list = getParamList().get(0).replaceFirst("\\[","").replaceFirst("\\]","");
        list = list.trim();
        String[] turtlesToActivate = list.split("\\s+");
        System.out.println("LIST: " + Arrays.toString(turtlesToActivate));
        for(Turtle turtle:turtles)
        {
            turtles.get(turtle.getId()-1).setActivated(false);
        }
        for(String activatedTurtle:turtlesToActivate)
        {
            int currentTurtleNum = Integer.parseInt(activatedTurtle);

            if(currentTurtleNum<=turtles.size())
            {
                turtles.get(currentTurtleNum-1).setActivated(true);
            }
            else
            {
                System.out.println("TURTLES.LENGTH: "+ turtles.size());
                for(int i = turtles.size();i<currentTurtleNum;i++)
                {
                    Turtle newTurtle = new Turtle();
                    newTurtle.setId(turtles.size());
                    if(i!=currentTurtleNum-1)
                    {
                        newTurtle.setActivated(false);
                    }
                    turtles.add(newTurtle);
                }
            }
            commandNode.setResult(currentTurtleNum+"");
        }
    }
}
