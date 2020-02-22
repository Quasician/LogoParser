package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.View.Visualiser;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    public static final String TITLE = "Slogo";
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
}
