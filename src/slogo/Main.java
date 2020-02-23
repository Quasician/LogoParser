package slogo;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.View.Visualiser;
import slogo.View.CommandLine;
import slogo.View.Drawing;
import slogo.View.Toolbar;
import slogo.View.TurtleGrid;
import slogo.View.ViewButton;
import slogo.View.Visualizer;
import slogo.model.CommandParser;
import slogo.model.Turtle;
import slogo.model.VariableHashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    //  public static final String LANGUAGE = "English";
    private static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    private static final String TURTLE_PNG = "turtle.png";
    //  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
    public static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");

    public static void main (String[] args) {
        launch(args);
    }

    private void bindTurtles(Turtle model, Turtle view) {
        view.xProperty().bind(model.xProperty());
        view.yProperty().bind(model.yProperty());
        view.distanceProperty().bind(model.distanceProperty());
        view.angleProperty().bind(model.angleProperty());
        view.isPenDownProperty().bind(model.isPenDownProperty());
        view.isShowingProperty().bind(model.isShowingProperty());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Double deg = Math.toDegrees(Math.atan2(-1, 0));
//        if(deg<0)
//        {
//            deg +=360;
//        }
//        System.out.println(deg);
        Turtle modelTurtle = new Turtle();
        Turtle viewTurtle = new Turtle();
        bindTurtles(modelTurtle, viewTurtle);

        //ObjectProperty<Turtle> modelTurtleProp = new SimpleObjectProperty<>(modelTurtle, "modelTurtle");
        //ObjectProperty<Turtle> viewTurtleProp = new SimpleObjectProperty<>(viewTurtle, "viewTurtle");
        // viewTurtleProp.bind(modelTurtleProp);

        modelTurtle.setX(20);
        System.out.println("Turtle x " + viewTurtle.getX());

        modelTurtle.setY(30);
        System.out.println("Turtle y " + viewTurtle.getY());

        modelTurtle.setDegree(49.9);
        System.out.println("Turtle degree " + viewTurtle.getDegree());

        CommandParser commandParser = new CommandParser(modelTurtle);
        commandParser.addPatterns("English");

//        modelTurtle.setDegree(45);
//        commandParser.parseText("towards -5 0");
      //  System.out.println("Hello world");
       // commandParser.parseText("difference difference 5 5 5");
        //System.out.println("Hello world");
        //Error if you put .decimal instead of 0.decimal -> 0.3 vs .3
//        commandParser.parseText("fd sum 10 sum 10 sum 10 sum 20 20");
//          commandParser.parseText("sum 10 sum 20 20");
//          printVariables();
        commandParser.parseText("make :v sum 23 3");
        commandParser.parseText("sum :v 14");
//        commandParser.parseText("atan product random quotient remainder product log 3.4 2 2 0.19 pi");

      Visualizer vis = new Visualizer(primaryStage, viewTurtle);
    }

    private void printVariables()
    {
        Iterator it = VariableHashMap.getAllVariables().iterator();
        System.out.println("\nTHESE ARE THE CURRENT VARIABLES: ");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next(); //current entry in a loop
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
//        for(Map.Entry e :vars)
//        {
//            System.out.println(e.getKey() + " = " + e.getValue());
//        }
    }
}
