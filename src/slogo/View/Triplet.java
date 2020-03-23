package slogo.View;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Triplet class based off of Triplet class from  https://stackoverflow.com/questions/6010843/java-how-to-store-data-triple-in-a-list
 * This class holds 3 objects in 1 data structure that has getters and String Properties.
 *
 * Purpose: To hold 3 objects and return the string property of each object's string representation
 *
 * Assumptions: That each object has a .toString defined
 *
 * Dependencies: StringProperty class
 *
 * Example:
 * <hr><blockquote><pre>
 *   String a = "a";
 *   String b = "b";
 *   String c = "c";
 *   Triplet<String, String, String> trip = new Triplet(a, b, c);
 * </pre></blockquote><hr>
 * @param <T> is the type of the first object
 * @param <U> is the type of the second object
 * @param <V> is the type of the third object
 *
 * @author Michelle Tai & Stack Overflow
 */
public class Triplet<T, U, V> {

  private final T first;
  private final U second;
  private final V third;

  /**
   * Constructor class that sets the values for each object in the data structure
   * @param first is the value of the first object of type T
   * @param second is the value of the second object of type U
   * @param third is the calue of the third object of type V
   */
  public Triplet(T first, U second, V third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  /**
   * @return the "first" object (with the correct type)
   */
  public T getFirst() { return first; }

  /**
   * @return the "second" object (with the correct type)
   */
  public U getSecond() { return second; }

  /**
   * @return the "third" object (with the correct type)
   */
  public V getThird() { return third; }

  /**
   * @return a StringProperty object of the first object's String representation
   */
  public StringProperty getFirstStringProperty(){
    return new SimpleStringProperty(first.toString());
  }

  /**
   * @return a StringProperty object of the second object's String representation
   */
  public StringProperty getSecondStringProperty(){
    return new SimpleStringProperty(second.toString());
  }

  /**
   * @return a StringProperty object of the third object's String representation
   */
  public StringProperty getThirdStringProperty(){
    return new SimpleStringProperty(third.toString());
  }

}
