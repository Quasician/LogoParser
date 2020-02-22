package slogo.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Drawing {
    private Color penColor= Color.BLACK;
    private Color backgroundColor= Color.BLACK;

    public void changeBackground(Color value){
        penColor=value;
    }
    public void changePen(Color newColor){
        backgroundColor=newColor;
    }


}
