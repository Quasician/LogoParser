package slogo.View;

import javafx.scene.Node;

/**
 * Every history view, such as the command history, outputview, and all the other panels, implements this class, since every view needs to return a view/node
 * that can be accessed when the corresponding tab button is pressed for that view. For example, in our code, when the tab/button with the "Command" text is
 * pressed, we need to show the node that holds that information, which happens to be a ListView.
 *
 * Purpose: To ensure that every object within the "HistoryPanel" area of the code will return the ndoe that displays the appropriate information.
 * By creating this interface, every object that implements it must return a Node object.
 *
 * Assumptions: That there is a Node to be returned?
 *
 * Dependencies: Node class
 *
 * Example:
 * <hr><blockquote><pre>
 *   public class VariableHistory implements HistoryView {
 *      private VBox variableHist;
 *      public VariableHistory() {
 *         ...
 *      }
 *
 *      public Node returnScene() {
 *        return variableHist;
 *      }
 *   }
 * </pre></blockquote><hr>
 * And then within Main, there might be something like
 * <hr><blockquote><pre>
 *   private HBox HistoryPanelArea;
 *   private VariableHistory vh;
 *   HistoryPanelArea.getChildren.add(vh.returnScene);
 * </pre></blockquote><hr>
 *
 * @author Michelle Tai
 */

interface HistoryView {

  /**
   * This is a getter class for the Node that holds the "scene", or all the visual things of the object. It's not named the best, but
   * I kept this name because when refactoring, most other classes that would implement this interface had a method named that, and I didn't have time
   * to go back and change it.
   * @return Node that holds all other nodes of the scene/panel
   */
  public Node returnScene();


}
