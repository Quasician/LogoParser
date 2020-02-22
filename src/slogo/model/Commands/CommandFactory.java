package slogo.model.Commands;

import javafx.scene.control.Alert;

import java.lang.reflect.InvocationTargetException;

//merge
public class CommandFactory {

    public static Command getCommandInstance(String commandClass) {
        try{
            return (Command) Class.forName(commandClass).getConstructors()[0].newInstance(commandClass);
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            //printStackTrace();
            showError("Class doesn't exist");
        }
        return null;
    }

    private static void showError(String mes)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(mes);
        alert.showAndWait();
    }
}
