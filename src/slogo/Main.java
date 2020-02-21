package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.CommandParser;

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
        System.out.println("Hello world");
        CommandParser parser = new CommandParser("Enhglish");
        System.out.println(parser.getSymbol("csg"));
    }
}
