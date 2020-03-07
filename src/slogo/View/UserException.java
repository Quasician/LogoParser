package slogo.View;

import javafx.scene.control.Alert;

import javax.sound.midi.SysexMessage;

public class UserException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception based on an issue in our code.
   */
  public UserException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   */
  public UserException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   */
  public UserException(Throwable cause) {
    showMessage(Alert.AlertType.ERROR, cause.getMessage());
  }

  public void showMessage (Alert.AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

}