package slogo;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import slogo.View.Language;
import slogo.View.Visualizer;
import slogo.model.CommandException;
import slogo.model.CommandParser;
import slogo.model.CustomCommandMap;
import slogo.model.Turtle;
import slogo.model.VariableHashMap;

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

        Language language = new Language();


        CommandParser commandParser = new CommandParser(modelTurtle, language);


//        modelTurtle.setDegree(45);
//        commandParser.parseText("towards -5 0");
      //  System.out.println("Hello world");
       // commandParser.parseText("difference difference 5 5 5");
        //System.out.println("Hello world");
        //Error if you put .decimal instead of 0.decimal -> 0.3 vs .3
//        commandParser.parseText("fd sum 10 sum 10 sum 10 sum 20 20");
//          commandParser.parseText("sum 10 sum 20 20");
//          printVariables();
//        commandParser.parseText("make :v sum 23 3");
//        commandParser.parseText("sum :v 14");
//        commandParser.parseText("atan product random quotient remainder product log 3.4 2 2 0.19 pi");

        //printVariables();
        StringProperty commandLinetext = new SimpleStringProperty(){};
        StringProperty parseString = new SimpleStringProperty(){};
        parseString.bind(commandLinetext);
        BooleanProperty textUpdate = new SimpleBooleanProperty();


        //commandParser.parseText("fd 50 rt 90 fd 50 rt 90 fd 50 rt 90 fd 50 rt 90");
//        commandParser.parseText("make :v 56");
//        printVariables();
//        commandParser.parseText("to dash [ :count ]\n" +
//                "[\n" +
//                "  repeat :count \n" +
//                "  [\n" +
//                "    pu fd 4 pd fd 4\n" +
//                "  ]      \n" +
//                "]");
//        printCustomCommands();


        Visualizer vis = new Visualizer(primaryStage, viewTurtle, commandLinetext, textUpdate, language, commandParser);
        parseTextOnInput(textUpdate, parseString, commandParser,vis);

        //commandParser.parseText("repeat 5 [ fd 100 rt 144 ] ");
//        modelTurtle.setX(-200);
//        System.out.println("Turtle x " + viewTurtle.getX());
//
//        modelTurtle.setY(280);
//        System.out.println("Turtle y " + viewTurtle.getY());
//
//        //modelTurtle.setDegree(49.9);
//        System.out.println("Turtle degree " + viewTurtle.getDegree());
    }

    private void printVariables()
    {
        Iterator it = VariableHashMap.getAllVariables().iterator();
        System.out.println("\nTHESE ARE THE CURRENT VARIABLES: ");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next(); //current entry in a loop
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private void printCustomCommands()
    {
        Iterator it = CustomCommandMap.getAllCustomCommands().iterator();
        System.out.println("\nTHESE ARE THE CURRENT CUSTOM COMMANDS: ");
        while (it.hasNext()) {
            String entry = (String) it.next(); //current entry in a loop
            System.out.println(entry);
        }
    }

    private void bindTurtles(Turtle model, Turtle view) {
//        view.xProperty().bind(model.xProperty());
//        view.yProperty().bind(model.yProperty());
        view.distanceProperty().bind(model.distanceProperty());
        view.angleProperty().bind(model.angleProperty());
        view.isPenDownProperty().bind(model.isPenDownProperty());
        view.isShowingProperty().bind(model.isShowingProperty());
        view.coordinatesProperty().bind(model.coordinatesProperty());
        view.clearScreenProperty().bind(model.clearScreenProperty());
    }



    //Sanna changed this method to do error checking
    private void parseTextOnInput(BooleanProperty textUpdate, StringProperty parseText, CommandParser commandParser,Visualizer vis)
    {
        textUpdate.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                System.out.println(parseText.getValue());
                //commandParser.parseText(parseText.getValue());
                //vis.makeNewBox(parseText.getValue());

                try {
                    commandParser.parseText(parseText.getValue());
                    vis.makeNewBox(parseText.getValue());
                } catch (CommandException e) {
                    showError(e.getMessage());
                }
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert test - error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
