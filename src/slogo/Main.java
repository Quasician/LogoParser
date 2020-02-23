package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
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
    /**
     * Start of the program.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        Visualiser logo = new Visualiser();
        primaryStage.setScene(logo.Screen());
        primaryStage.show();
    }
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Double deg = Math.toDegrees(Math.atan2(-1, 0));
//        if(deg<0)
//        {
//            deg +=360;
//        }
//        System.out.println(deg);
        Turtle turtle = new Turtle();
        CommandParser commandParser = new CommandParser(turtle);
        commandParser.addPatterns("English");

        turtle.setDegree(45);
        commandParser.parseText("towards -5 0");
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
