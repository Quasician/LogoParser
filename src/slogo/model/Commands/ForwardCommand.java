package slogo.model.Commands;


import java.util.ArrayList;

public class ForwardCommand extends Command {

    public ForwardCommand()
    {
        super(1);
    }
    @Override
    public void doCommand() {
        System.out.println("\nDID FORWARD COMMAND by "+values[0]);
    }
}
