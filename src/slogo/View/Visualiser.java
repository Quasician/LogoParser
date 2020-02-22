package slogo.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Visualiser {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static Toolbar bar;
    private static final Paint SCREEN_BACKGROUND = Color.web("0e1e38");


    public Scene Screen(){
        VBox root = new VBox(20);
        bar=new Toolbar();

        root.getChildren().add(bar.ToolBar());
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }
}
