package slogo.View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class creates a custom button for our code, which is adapted from Franklin Boampong's button code in our Simulation project (team 13)
 * This code is styled with hover effects, and is reusable since we can set the text easily
 */

public class ViewButton extends Button {

  private static final int DEFAULT_SIZE = 50;

  /**
   * Constructor method that lays out the basic view of a button in our parser
   * @param text specifies text in button that corresponds with the value left value in the properties file
   * @author Michelle Tai, adapted from Franklin Boampong's button code in our Simulation project
   */
  public ViewButton(String text, int height, int width) {
    setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));
    setText(text);
    setPrefHeight(height);//45
    setPrefWidth(width);//190
    mouseUpdateListener();
  }

  /**
   * default constructor
   */
  public ViewButton(){
    this("", DEFAULT_SIZE, DEFAULT_SIZE);
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
