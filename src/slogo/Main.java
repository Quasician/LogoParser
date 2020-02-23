package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.CommandParser;
import slogo.model.Turtle;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
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
        //Error if you put .decimal instead of 0.decimal -> 0.3 vs .3
//        commandParser.parseText("fd sum 10 sum 10 sum 10 sum 20 20");
//        commandParser.parseText("sum 10 sum 20 20");
        commandParser.parseText("atan product random quotient remainder product log 3.4 2 2 0.19 pi");
    }
}
