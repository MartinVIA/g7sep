package utils;

import model.*;
import java.util.*;
import java.io.*;
import java.time.LocalTime;

public class FileWriter {
        private ClovervilleModelManager model;
        private FileXMLLogger xmlLogger;

        public FileWriter(ClovervilleModelManager model) {
                this.model = model;
                this.xmlLogger = new FileXMLLogger("src/file_operations.xml");
        }

        public void saveAllData() {
                saveResidents();
                savePersonalPoints();
                saveTasks();
                saveTrades();
        }

        public void saveResidents() {
                ResidentList residentList = new ResidentList();
                for (Resident resident : model.getAllResidents()) {
                        residentList.addResident(resident);
                }

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("customers.bin"))) {
                        out.writeObject(residentList);
                        System.out.println("Success writing residents");

                        // Log to XML
                        List<String> residentData = new ArrayList<>();
                        for (Resident r : model.getAllResidents()) {
                                residentData.add(r.getId() + "," + r.getFirstName() + "," + r.getLastName());
                        }
                        xmlLogger.logWrite("Residents", residentData);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void savePersonalPoints() {
                try {
                        List<String> pointsData = new ArrayList<>();
                        for (Resident r : model.getAllResidents()) {
                                pointsData.add(r.getId() + "," + r.getPersonalPoints());
                        }
                        xmlLogger.logWrite("PersonalPoints", pointsData);
                        System.out.println("Logged resident points to XML");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void saveTasks() {
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("tasks.bin"))) {
                        TasksList tasksList = new TasksList();

                        for (Task task : model.getAllTasks()) {
                                tasksList.addTask(task);
                        }

                        out.writeObject(tasksList);
                        System.out.println("Success writing tasks");

                        // Log to XML
                        List<String> taskData = new ArrayList<>();
                        for (Task t : model.getAllTasks()) {
                                taskData.add(t.getName() + "," + t.getType());
                        }
                        xmlLogger.logWrite("Tasks", taskData);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void saveTrades() {
                // Note: trades are stored in ClovervilleModelManager, but we need a getter for
                // them
                System.out.println("Trades saving not fully implemented yet (need getTrades method in model)");
        }

        public static void main(String[] args) {
                // For standalone testing with hardcoded data
                ResidentList residentList = new ResidentList();
                residentList.addResident(new Resident(1, "Green", "Bob", 9999999));
                residentList.addResident(new Resident(2, "Green", "Smith", 0));
                residentList.addResident(new Resident(3, "Charlie", "Brown", 0));
                residentList.addResident(new Resident(4, "Diana", "White", 0));

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("customers.bin"))) {
                        out.writeObject(residentList);
                        System.out.println("Success writing residents");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // Log resident points to XML
                try {
                        List<String> pointsData = new ArrayList<>();
                        for (Resident r : residentList.getAllResidents()) {
                                pointsData.add(r.getId() + "," + r.getPersonalPoints());
                        }
                        FileXMLLogger xmlLogger = new FileXMLLogger("src/file_operations.xml");
                        xmlLogger.logWrite("PersonalPoints", pointsData);
                        System.out.println("Logged resident points to XML");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Done writing");
        }
}