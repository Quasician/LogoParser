
import java.awt.*;
import java.util.*;
import javafx.scene.paint.Color;


/**
 *
 */
public interface ViewInternal {
    /**
     * @param color
     */
    public void changeColorPen(Color color);

    /**
     * @param color
     */
    public void changeColorBackground(Color color);

    /**
     * @param isPenDown
     */
    public void draw(boolean isPenDown);

}
