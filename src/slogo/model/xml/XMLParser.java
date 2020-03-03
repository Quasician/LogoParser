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

public class XMLParser {

  private static final String NAME = "Name";
  private static final String COMMANDS = "Commands";
  private static final String COMMAND = "Command";
  private static final String RESOURCES_ERROR_MESSAGES = "resources.ErrorMessages";
  private static final String STYLING = "Styling";
  private static final String STYLE_INSTEAD_OF_SIM = "StyleInsteadOfSim";

  private File configFile;
  private DocumentBuilder DOCUMENT_BUILDER;
  private Element root;
  private Element commandElement;
  private Document doc;
  private String name;
  private ArrayList<String> setUpVals = new ArrayList<>();

  private ResourceBundle errors = ResourceBundle.getBundle(RESOURCES_ERROR_MESSAGES);

  public XMLParser(File config) {
    configFile = config;
  }

  /**
   * Gets set-up parameters from XML config file and puts them in a list
   * @return arraylist with set up parameters for simulation
   */
  public List<String> getSetUpParameters() {
    DOCUMENT_BUILDER = getDocumentBuilder();
    try {
      doc = DOCUMENT_BUILDER.parse(configFile);
      doc.getDocumentElement().normalize();
      root = doc.getDocumentElement();
    } catch (SAXException | IOException e) {
      throw new XMLException(e);
    }

    name = root.getElementsByTagName(NAME).item(0).getTextContent();
    readGeneralParameters();
    return setUpVals;
  }

  private void readGeneralParameters() {
    NodeList nodeList = root.getElementsByTagName(COMMANDS);
    for (int x = 0; x < nodeList.getLength(); x++) {
      Node node = nodeList.item(x);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        commandElement = (Element) (node);
      }
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
    NodeList nodeList1 = root.getElementsByTagName("Command");
    for (int x = 0; x < nodeList1.getLength(); x++) {
      Node node = nodeList1.item(x);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        commandElement = (Element) (node);
        String command = getTextByTag(COMMAND, 0);
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
