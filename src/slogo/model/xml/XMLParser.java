package slogo.model.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Sanna
 *
 * This class uses Saurav Sanjay's code from my simulation team as a reference. This is used
 * to parse an uploaded XML file, so that users can choose and run a set of commands.
 */
public class XMLParser {

  private static final String COMMAND = "Command";
  private static final String TEXT = "Text";
  private static final String RESOURCES_ERROR_MESSAGES = "resources.ErrorMessages";
  private File configFile;
  private DocumentBuilder DOCUMENT_BUILDER;
  private Element root, commandElement;
  private Document doc;
  private ResourceBundle errors = ResourceBundle.getBundle(RESOURCES_ERROR_MESSAGES);

  /**
   * XMLParser constructor
   * @param config the file to parse
   */
  public XMLParser(File config) {
    configFile = config;
  }

  /**
   * Gets set-up parameters from XML config file and puts them in a list
   */
  public void setUp() {
    DOCUMENT_BUILDER = getDocumentBuilder();
    try {
      doc = DOCUMENT_BUILDER.parse(configFile);
      doc.getDocumentElement().normalize();
      root = doc.getDocumentElement();
    } catch (SAXException | IOException | IllegalArgumentException e) {
      throw new XMLException(e);
    }
  }

  private String getTextByTag(String tag, int itemNumber) {
    return commandElement.getElementsByTagName(tag).item(itemNumber).getTextContent();
  }

  /**
   * Gets specific command information from XML file
   * @return list with information about command
   */
  public List<String> getCommands() {
    ArrayList<String> specificCommand = new ArrayList<>();
    NodeList nodeList = root.getElementsByTagName(COMMAND);
    for (int x = 0; x < nodeList.getLength(); x++) {
      Node node = nodeList.item(x);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        commandElement = (Element) (node);
        String command = getTextByTag(TEXT, 0);
        specificCommand.add(command);
      }
    }
    return specificCommand;
  }

  private DocumentBuilder getDocumentBuilder() {
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new XMLException(e);
    }
  }
}
