package slogo.model.Commands;

import java.util.List;

public interface CommandFactoryInterface {
    public Command createCommand(String command, List params);
}
