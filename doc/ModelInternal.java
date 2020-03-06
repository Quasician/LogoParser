
import java.util.*;

/**
 *
 */
public interface ModelInternal {

    /**
     * @param  x
     */
    public void setTurtleX(int x);

    /**
     * @param  y
     */
    public void setTurtleY(int y);

    /**
     *
     */
    public void getTurtleX();

    /**
     *
     */
    public void getTurtleY();

    /**
     *
     */
    public void getDegree();

    /**
     * @param degree
     */
    public void setDegree(int degree);

    /**
     *
     */
    public void penUp();

    /**
     *
     */
    public void penDown();

    /**
     *
     */
    public void isPenUp();

    /**
     *
     */
    public void isPenDown();

    /**
     * @param distance
     */
    public void setDistance(int distance);

    /**
     *
     */
    public void getDistance();

    /**
     *
     */
    public void doCommand();

}
