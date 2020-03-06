package slogo.model.xml;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreator {
  protected static final String COMMAND = "Command";
  protected static final String COMMANDS = "Commands";
  protected static final String TEXT = "Text";
  protected static final String TITLE = "Title";
  protected static final String NAME = "Name";
  protected static final String AUTHOR = "author";
  protected static final String AUTHOR_NAME = "Sanna"; //put in resource file
  private static final String RESOURCES_ERROR_MESSAGES = "resources.ErrorMessages";

  private DocumentBuilderFactory DOCUMENT_FACTORY;
  private DocumentBuilder DOCUMENT_BUILDER;
  private Document document;
  private ResourceBundle errors = ResourceBundle.getBundle(RESOURCES_ERROR_MESSAGES);
  protected Element element;
  private List<String> myCommands;
  private File mySaveFile;

  /**
   * Creates an XML file creator
   */
  public XMLCreator(List<String> commands, File saveFile) {
    myCommands = commands;
    mySaveFile = saveFile;
  }

  /**
   * Creates an XML file that saves current state of simulation
   */
  public void createFile(String simTitle) {
    setUpInitialFileParameters(simTitle);
    inputCommands();
    outputFile();
  }

  protected void outputFile() {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(mySaveFile);
      StreamResult console = new StreamResult(System.out);
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, result);
      transformer.transform(source, console);
    } catch (Exception e) {
      //throw new XMLException(e, "some error"); //TODO
    }
  }

  protected void inputCommands() {
    System.out.println(myCommands.size());
    for (int i = 0; i < myCommands.size(); i++) {
      String command = myCommands.get(i);
      System.out.println(command);
      Element commandElement = document.createElement(COMMAND);
      element.appendChild(commandElement);
      createXMLElement(TEXT, command, commandElement);
    }
  }

  protected void setUpInitialFileParameters(String title) {
    document = makeDocument();
    Element root = document.createElement(TITLE);
    root.setAttribute(NAME, title);
    document.appendChild(root);
    createXMLElement(AUTHOR, AUTHOR_NAME, root);

    element = document.createElement(COMMANDS);
    root.appendChild(element);
  }

  protected Element createXMLElement(String tagName, String data, Element root) {
    Element e = document.createElement(tagName);
    e.appendChild(document.createTextNode(data));
    root.appendChild(e);
    return e;
  }

  protected Document makeDocument() {
    DOCUMENT_FACTORY = DocumentBuilderFactory.newInstance();
    try {
      DOCUMENT_BUILDER = DOCUMENT_FACTORY.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new XMLException(e, "someerror");
    }
    return DOCUMENT_BUILDER.newDocument();
  }
}
