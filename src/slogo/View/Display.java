package slogo.View;

import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import slogo.model.DisplayOption;

public class Display {
    private Stage userStage;
    private StringProperty commandText;
    private Language displayLang;
    private DisplayOption options;
    public Display(Stage primaryStage, StringProperty commandText, Language displayLanguages, DisplayOption options){
        this.userStage=primaryStage;
        this.commandText=commandText;
        this.displayLang= displayLanguages;
        this.options=options;
    }

    public Stage getUserStage() {
        return userStage;
    }

    public StringProperty getCommandText() {
        return commandText;
    }

    public Language getDisplayLang() {
        return displayLang;
    }

    public DisplayOption getOptions() {
        return options;
    }
}
