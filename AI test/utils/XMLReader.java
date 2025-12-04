package utils;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import model.*;

public class XMLReader {

    public static List<Resident> readResidentsFromXML(String filePath) {
        List<Resident> residents = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("XML file not found: " + filePath);
                return residents;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Get all Operation elements
            NodeList operations = document.getElementsByTagName("Operation");

            for (int i = 0; i < operations.getLength(); i++) {
                Element operation = (Element) operations.item(i);

                // Get the Data element
                NodeList dataNodes = operation.getElementsByTagName("Data");
                if (dataNodes.getLength() > 0) {
                    Element dataElement = (Element) dataNodes.item(0);
                    NodeList lines = dataElement.getElementsByTagName("Line");

                    // Parse each Line element
                    for (int j = 0; j < lines.getLength(); j++) {
                        Element lineElement = (Element) lines.item(j);
                        String lineText = lineElement.getTextContent();

                        // Parse format: ID,FirstName,LastName
                        String[] parts = lineText.split(",");
                        if (parts.length >= 3) {
                            try {
                                int id = Integer.parseInt(parts[0].trim());
                                String firstName = parts[1].trim();
                                String lastName = parts[2].trim();

                                // Create resident with 0 points (will be loaded separately)
                                Resident resident = new Resident(id, lastName, firstName, 0);
                                residents.add(resident);
                                System.out.println(
                                        "Loaded resident: " + firstName + " " + lastName + " (ID: " + id + ")");
                            } catch (NumberFormatException e) {
                                System.err.println("Failed to parse resident line: " + lineText);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading residents XML: " + filePath);
            e.printStackTrace();
        }

        return residents;
    }

    public static Map<Integer, Integer> readPersonalPointsFromXML(String filePath) {
        Map<Integer, Integer> pointsMap = new HashMap<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("XML file not found: " + filePath);
                return pointsMap;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Get all Operation elements
            NodeList operations = document.getElementsByTagName("Operation");

            for (int i = 0; i < operations.getLength(); i++) {
                Element operation = (Element) operations.item(i);

                // Get the Data element
                NodeList dataNodes = operation.getElementsByTagName("Data");
                if (dataNodes.getLength() > 0) {
                    Element dataElement = (Element) dataNodes.item(0);
                    NodeList lines = dataElement.getElementsByTagName("Line");

                    // Parse each Line element
                    for (int j = 0; j < lines.getLength(); j++) {
                        Element lineElement = (Element) lines.item(j);
                        String lineText = lineElement.getTextContent();

                        // Parse format: ID,Points
                        String[] parts = lineText.split(",");
                        if (parts.length >= 2) {
                            try {
                                int id = Integer.parseInt(parts[0].trim());
                                int points = Integer.parseInt(parts[1].trim());
                                pointsMap.put(id, points);
                                System.out.println("Loaded points: ID=" + id + ", Points=" + points);
                            } catch (NumberFormatException e) {
                                System.err.println("Failed to parse points line: " + lineText);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading personal points XML: " + filePath);
            e.printStackTrace();
        }

        return pointsMap;
    }

    public static void main(String[] args) {
        // Test reading XML files
        System.out.println("Testing XMLReader...");
        List<Resident> residents = readResidentsFromXML("file_operations_residents.xml");
        System.out.println("Loaded " + residents.size() + " residents");

        Map<Integer, Integer> points = readPersonalPointsFromXML("file_operations_personalpoints.xml");
        System.out.println("Loaded points for " + points.size() + " residents");
    }
}
