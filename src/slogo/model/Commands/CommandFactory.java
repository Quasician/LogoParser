package slogo.model.Commands;

import javafx.scene.control.Alert;

import java.lang.reflect.InvocationTargetException;


//merge
public class CommandFactory implements CommandFactoryInterface {

  private static void showError(String mes) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(mes);
    alert.showAndWait();
  }

  @Override
  public Command createCommand(String commandClass) {
    System.out.println("Command Created");
    try {
      return (Command) Class.forName(commandClass).getConstructors()[0].newInstance(commandClass);
      //command.setParams(params);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      //printStackTrace();
      showError("Class doesn't exist");
    }
    return null;
  }
}
