package slogo.View;

import javafx.beans.property.BooleanProperty;

public class ActivityListeners {

  private BooleanProperty textUpdate;
  private BooleanProperty checkBox;
  private BooleanProperty translateTextUpdate;

  public ActivityListeners(BooleanProperty textUpdate, BooleanProperty checkBox,
      BooleanProperty miniTextUpdate) {
    this.textUpdate = textUpdate;
    this.checkBox = checkBox;
    this.translateTextUpdate = miniTextUpdate;
  }

  public BooleanProperty textUpdateProperty() {
    return textUpdate;
  }

  public BooleanProperty checkBoxProperty() {
    return checkBox;
  }

  public BooleanProperty translateTextUpdateProperty() {
    return translateTextUpdate;
  }
}
