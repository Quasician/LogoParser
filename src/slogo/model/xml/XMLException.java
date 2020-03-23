package slogo.model.xml;

import javafx.scene.control.Alert;

/**
 * This class represents what might go wrong when using XML files.
 *
 * @author Robert C. Duvall
 */

public class XMLException extends RuntimeException {

  // for serialization
  private static final long serialVersionUID = 1L;


  /**
   * Create an exception based on an issue in our code.
   */
  public XMLException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   */
  public XMLException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   */
  public XMLException(Throwable cause) {
    showMessage(Alert.AlertType.ERROR, cause.getMessage());
  }

  /**
   * Create an exception based on a message only
   */
  public XMLException(String error) {
    showMessage(Alert.AlertType.ERROR, error);
  }

  private void showMessage(Alert.AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }
}


