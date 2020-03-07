package slogo.View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * This class creates a custom button for our code, which is adapted from Franklin Boampong's button code in our Simulation project (team 13)
 * This code is styled with hover effects, and is reusable since we can set the text easily
 */

public class ViewButton extends Button {
  private static final String STYLE_COLOR = "lightgray";
  private static final int DEFAULT_FONT_SIZE = 20;
  private static final String FONT_FORMAT ="Calibri";
  private static final int DEFAULT_BUTTON_WIDTH = 65;
  private static final int DEFAULT_BUTTON_HEIGHT =45;
  private static final int DEFAULT_FONT_SIZE_SMALL =10;
  /**
   * Constructor method that lays out the basic view of a button in our parser
   * @param text specifies text in button that corresponds with the value left value in the properties file
   * @author Michelle Tai, adapted from Franklin Boampong's button code in our Simulation project
   */
  public ViewButton(String text, int height, int width, int fontSize) {
    setFont(Font.font(FONT_FORMAT, fontSize));
    setText(text);
    setPrefHeight(height);//45
    setPrefWidth(width);//190
    mouseUpdateListener();
  }

  public ViewButton(String text, int height, int width){
    this(text, height, width, DEFAULT_FONT_SIZE);
  }

  private void mouseUpdateListener() {
    setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        setEffect(new DropShadow());
      }
    });

    setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        setEffect(null);
      }
    });
  }
}
