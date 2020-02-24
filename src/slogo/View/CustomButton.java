package slogo.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CustomButton extends Button{
    public static Button CustomButton(String text, String styleColor, Paint fontColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(fontColor);
        button.setFont(Font.font("Calibri"));
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        button.setPrefWidth(200);
        return button;
    }
    public static ColorPicker pickColor(String title){
        ColorPicker cp = new ColorPicker();
        cp.setPrefWidth(50);
        cp.setAccessibleText(title);
        return cp;
    }
    public void setImage(CustomButton button, String image){
        Image img = new Image(image);
        ImageView buttonImage= new ImageView(img);
        buttonImage.setFitHeight(10);
        buttonImage.setFitWidth(10);
        button.setGraphic(buttonImage);

    }
}
