package slogo.model.Commands;

import javafx.scene.control.Alert;

import java.lang.reflect.InvocationTargetException;

public class CommandFactory {

    public static Command getCommandInstance(String commandClass) {
        try{
            return (Command) Class.forName(commandClass).getDeclaredConstructor().newInstance();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            //printStackTrace();
            showError("Class doesn't exist");
        }
        return new Command(0) {
            @Override
            public void doCommand() {
                //Dummy command
            }
        };
    }

    private static void showError(String mes)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(mes);
        alert.showAndWait();
    }
}
