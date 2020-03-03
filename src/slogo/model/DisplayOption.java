package slogo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class DisplayOption {
  private static final String COMMA = ", ";
  private static final String SPACE = " ";
  private ObservableList<String> colorOptions;
  private IntegerProperty penIndex = new SimpleIntegerProperty();
  private IntegerProperty bgIndex = new SimpleIntegerProperty();
  private DoubleProperty penWidth = new SimpleDoubleProperty();
  private IntegerProperty imageIndex = new SimpleIntegerProperty();

  public DisplayOption() {
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

  public double getPenWidth() {
    return penWidth.get();
  }

  public void createList(ObservableList list) {
    colorOptions = list;
  }

  public String getColorAt(int index) {
    return colorOptions.get(index);
  }

  public int getLargestIndex() {
    return colorOptions.size() - 1;
  }

  public void setCurrentChoicePen(int index) {
    penIndex.set(index);
  }

  public int getCurrentChoicePen() {
    return penIndex.get();
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

  public int getCurrentBackground() {
    return bgIndex.get();
  }

  public void addToList(String c) {
    String[] colorarray = c.split(COMMA);
    String color = colorarray[0];

    if (!colorOptions.contains(color)) {
      colorOptions.add(color + COMMA + colorOptions.size());
    }
  }

  public void setColorAt(int index, int[] rgb) {
    System.out.println("here color options set11");
    String[] rgbStrings = new String[rgb.length];
    for (int i = 0; i < rgbStrings.length; i++) {
      rgbStrings[i] = "" + rgb[i];
    }
    String rgbToString = String.join(SPACE, rgbStrings);

    if (index < colorOptions.size()) {
      System.out.println("here color options set2");
      colorOptions.set(index, rgbToString + COMMA + index);
      System.out.println("here color options set");
    } else {
      addToList(rgbToString);
    }
  }
}