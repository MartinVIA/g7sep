package utils;

import model.Resident;
import model.ResidentList;
import model.Date;

import java.io.*;
import java.util.ArrayList;

public class ResidentXML {
    private ResidentList residentList;
    private ArrayList<Resident> residents;

    public ResidentXML() {
        residents = new ArrayList<>();
        readResidents();
        writeResidents();
    }

    public ArrayList<Resident> getResidents() {
        return residents;
    }

    public void readResidents() {
        try (FileInputStream fileIn = new FileInputStream("residents.bin");
                ObjectInputStream read = new ObjectInputStream(fileIn)) {

            residentList = (ResidentList) read.readObject();

            // Populate the residents ArrayList from residentList
            residents = residentList.getAllResidents();

        } catch (FileNotFoundException e) {
            System.out.println("File not found, or could not be opened");
            // We might not want to exit if file doesn't exist, just have empty list
            // But following VIAPets example:
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IO Error reading file");
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void writeResidents() {
        try (FileOutputStream fileOut = new FileOutputStream("residents.xml");
                PrintWriter write = new PrintWriter(fileOut)) {

            write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            write.println("<residents>");
            for (Resident resident : residents) {
                write.println("<resident>");
                write.println("<id>" + resident.getId() + "</id>");
                write.println("<firstName>" + resident.getFirstName() + "</firstName>");
                write.println("<lastName>" + resident.getLastName() + "</lastName>");
                write.println("<hasBoost>" + resident.getHasBoost() + "</hasBoost>");
                write.println("<personalPoints>" + resident.getPersonalPoints() + "</personalPoints>");

                Date latestTask = resident.getLatestTask();
                if (latestTask != null) {
                    write.println("<latestTask>" + latestTask.toString() + "</latestTask>");
                } else {
                    write.println("<latestTask>null</latestTask>");
                }

                Date latestGreenAction = resident.getLatestGreenAction();
                if (latestGreenAction != null) {
                    write.println("<latestGreenAction>" + latestGreenAction.toString() + "</latestGreenAction>");
                } else {
                    write.println("<latestGreenAction>null</latestGreenAction>");
                }

                write.println("</resident>");
            }
            write.println("</residents>");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
