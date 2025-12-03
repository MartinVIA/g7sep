package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileXMLLogger {
    private String xmlFilePath;

    public FileXMLLogger(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public void logWrite(String fileName, List<String> data) {
        logOperation("write", fileName, data);
    }

    public void logRead(String fileName, List<String> data) {
        logOperation("read", fileName, data);
    }

    private void logOperation(String operation, String fileName, List<String> data) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;
            File xmlFile = new File(xmlFilePath);
            
            if (xmlFile.exists()) {
                doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("FileOperations");
                doc.appendChild(rootElement);
            }

            Element root = doc.getDocumentElement();
            Element opElem = doc.createElement("Operation");
            opElem.setAttribute("type", operation);
            opElem.setAttribute("file", fileName);

            Element dataElem = doc.createElement("Data");
            for (String line : data) {
                Element lineElem = doc.createElement("Line");
                lineElem.appendChild(doc.createTextNode(line));
                dataElem.appendChild(lineElem);
            }
            opElem.appendChild(dataElem);
            root.appendChild(opElem);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            
        } catch (ParserConfigurationException | IOException | TransformerException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }
}
