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
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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

            // Additionally write a JS data file for easy client-side loading without HTTP
            try {
                if ("write".equalsIgnoreCase(operation) && fileName != null && fileName.contains("PersonalPoints")) {
                    writeJsDataFile(data);
                }
            } catch (IOException ioe) {
                // non-fatal: log and continue
                ioe.printStackTrace();
            }
        } catch (ParserConfigurationException | IOException | TransformerException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }

    private void writeJsDataFile(List<String> data) throws IOException {
        // Write a small JS file that assigns a global variable with the personal points
        File jsFile = new File("docs/file_operations_data.js");
        // Build JSON array
        StringBuilder sb = new StringBuilder();
        sb.append("window.fileOperations = window.fileOperations || {};\n");
        sb.append("window.fileOperations.personalPoints = [\n");
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i).trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split("[,;\\s]+");
            String id = parts.length > 0 ? parts[0] : "";
            String points = parts.length > 1 ? parts[1] : "0";
            sb.append("  {\"id\": \"").append(escapeForJson(id)).append("\", \"points\": \"")
                    .append(escapeForJson(points)).append("\" }");
            if (i < data.size() - 1)
                sb.append(",\n");
            else
                sb.append('\n');
        }
        sb.append("];\n");
        String time = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC).format(Instant.now());
        sb.append("window.fileOperations.lastUpdated = \"").append(time).append("\";\n");

        try (java.io.FileWriter fw = new java.io.FileWriter(jsFile, false)) {
            fw.write(sb.toString());
        }
    }

    private String escapeForJson(String s) {
        if (s == null)
            return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}
