
import java.util.*;


/**
 *
 */
public interface ViewInternal {
    /**
     * @param Color color
     */
    public void changeColorPen(Color color);

    /**
     * @param Color color
     */
    public void changeColorBackground(Color color);

    /**
     * @param boolean isPenDown
     */
    public void draw(boolean isPenDown);

}
