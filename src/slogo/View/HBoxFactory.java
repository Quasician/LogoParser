package slogo.View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxFactory {
    Label X_coord;
    Label Y_coord;
    HBox newBox;
    private static final String ID_STRING="Turtle ID: ";
    private static final String X_STRING= " X coordinate: ";
    private static final String Y_STRING=" Y coordinate of : ";
    private static final String SPACE=" ";
    public HBoxFactory(double x, double y, String name) {
        Y_coord=new Label(Y_STRING+y);
        X_coord= new Label(name+SPACE+X_STRING+x);
        newBox= new HBox();
        newBox.getChildren().addAll(X_coord,Y_coord);
    }
    public Label XCord(){
        return X_coord;
    }
    public void SetXCord(int id, double x){
       X_coord.setText(id+ SPACE+X_STRING+x);
    }
    public Label YCord(){
        return Y_coord;
    }
    public void SetYCord(int id, double y){
        Y_coord.setText(Y_STRING+y);
    }
    public HBox newBox(){
        return newBox;
    }
}
