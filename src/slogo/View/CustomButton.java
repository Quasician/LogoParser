package slogo.View;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;

public class CustomButton {
    public static Button CustomButton(String text, String styleColor, Paint fontColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(fontColor);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        button.setPrefWidth(200);
        return button;
    }
    public static ColorPicker pickColor(String title){
        ColorPicker cp = new ColorPicker();
        cp.setAccessibleText(title);
        return cp;
    }
}
