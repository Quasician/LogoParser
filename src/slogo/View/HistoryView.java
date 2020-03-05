package slogo.View;

import javafx.scene.Node;

/**
 * Every history view implements this class, since every history view needs to return a view/node
 */

interface HistoryView {

  /**
   * The node that is returned that holds the general history view
   * @return
   */
  public Node returnScene();


}
