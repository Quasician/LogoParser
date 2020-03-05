package slogo.View;


/**
 * Triplet class taken from https://stackoverflow.com/questions/6010843/java-how-to-store-data-triple-in-a-list
 */
public class StringTriplet extends Triplet {

  private final String first;
  private final String second;
  private final String third;

  public StringTriplet(String first, String second, String third) {
    super(first, second, third);
    this.first = first;
    this.second = second;
    this.third = third;
  }



//  public StringProperty getFirstStringProperty{
//    return new SimpleStringProperty().setValue(first.toString());
//  }

}
