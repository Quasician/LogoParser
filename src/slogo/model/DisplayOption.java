package slogo.model;

import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DisplayOption {
  private static final String COMMA = ", ";
  private static final String SPACE = " ";
  private List<String> colorOptions;
  private IntegerProperty penIndex = new SimpleIntegerProperty();
  private IntegerProperty bgIndex = new SimpleIntegerProperty();
  private DoubleProperty penWidth = new SimpleDoubleProperty();
  private IntegerProperty imageIndex = new SimpleIntegerProperty();
  private IntegerProperty changedIndex = new SimpleIntegerProperty(-1);
  private StringProperty newColor = new SimpleStringProperty();

  public DisplayOption() {
  }

  public StringProperty getNewColor() {
    return newColor;
  }

  public IntegerProperty getChangedIndex() {
    return changedIndex;
  }

  public IntegerProperty getImageIndex() {
    return imageIndex;
  }

  public IntegerProperty getPenIndex() {
    return penIndex;
  }

  public IntegerProperty getBgIndex() {
    return bgIndex;
  }

  public DoubleProperty getPenWidthProperty() {
    return penWidth;
  }

  public void createList(List list) {
    colorOptions = list;
  }

  public int getLargestIndex() {
    return colorOptions.size() - 1;
  }

  public void setCurrentChoicePen(int index) {
    penIndex.set(index);
  }

  public void setImageIndex(int index) {
    imageIndex.set(index);
  }

  public void setCurrentBackground(int index) {
    bgIndex.set(index);
  }

  public void setPenWidth(double width) {
    penWidth.set(width);
  }

  public void addToList(String c) {
    String[] colorarray = c.split(COMMA);
    String color = colorarray[0];
    colorOptions.add(color + COMMA + colorOptions.size());
  }

  public void setColorAt(int index, int[] rgb) {
    String[] rgbStrings = new String[rgb.length];
    for (int i = 0; i < rgbStrings.length; i++) {
      rgbStrings[i] = "" + rgb[i];
    }
    String rgbToString = String.join(SPACE, rgbStrings);
    newColor.set(rgbToString + COMMA + index);
    changedIndex.set(index);
    if (index < colorOptions.size()) {
      colorOptions.set(index, newColor.get());
    } else {
      addToList(rgbToString);
    }
  }
}