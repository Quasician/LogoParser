package slogo.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Drawing {
    private Color penColor= Color.BLACK;
    private Color backgroundColor= Color.BLACK;
    private static boolean penState=true;

    public void changeBackground(Color value){
        penColor=value;
    }
    public void changePen(Color newColor){
        backgroundColor=newColor;
    }
    public boolean penState(){
        return !penState;
    }

    public void drawLine(int a, int b){
        if(penState){
            draw(a, b);
        }
       // move the turtle from a to b

    }

    private void draw(int a, int b) {
        Line newLine= new Line();
        newLine.setFill(penColor);
        newLine.setStartX(a);
        newLine.setStartY(a);
        newLine.setEndX(b);
        newLine.setEndY(b);
    // not part of api, but we can change this later?
//    protected Color getBackgroundColor(){
//        return backgroundColor;
    }

}
