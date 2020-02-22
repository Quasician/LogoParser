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
        Turtle turtle = new Turtle();
        CommandParser commandParser = new CommandParser(turtle);
        commandParser.addPatterns("English");
        commandParser.parseText("towards 0 -10");
        System.out.println("Hello world");
       // commandParser.parseText("difference difference 5 5 5");
        //System.out.println("Hello world");
    }
}
