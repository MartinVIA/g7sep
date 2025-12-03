package utils;

import model.CustomerList;
import model.*;
import model.Pets.PetList;

import java.io.*;

public class FileReader {
    public static void main(String[] args) {
        try {
            FileInputStream customersFileIn = new FileInputStream("customers.bin");
            ObjectInputStream readCustomers = new ObjectInputStream(customersFileIn);
            try {
                CustomerList customerList = (CustomerList) readCustomers.readObject();
                for (int i = 0; i < customerList.size(); i++) {
                    System.out.println(customerList.get(i));
                }
            } catch (EOFException eof) {
                System.out.println("End of file");
            }
            readCustomers.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, or could not be opened");
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

        try {
            FileInputStream petsFileIn = new FileInputStream("pets.bin");
            ObjectInputStream readPets = new ObjectInputStream(petsFileIn);
            try {
                PetList petList = (PetList) readPets.readObject();
                for (int i = 0; i < petList.size(); i++) {
                    System.out.println(petList.get(i));
                }
            } catch (EOFException eof) {
                System.out.println("End of file");
            }
            readPets.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, or could not be opened");
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

        try {
            FileInputStream bookingsFileIn = new FileInputStream("bookings.bin");
            ObjectInputStream readBookings = new ObjectInputStream(bookingsFileIn);
            try {
                KennelList kennelList = (KennelList) readBookings.readObject();
                for (int i = 0; i < kennelList.size(); i++) {
                    System.out.println(kennelList.get(i));
                }
            } catch (EOFException eof) {
                System.out.println("End of file");
            }
            readBookings.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, or could not be opened");
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

        try {
            FileInputStream purchasesFileIn = new FileInputStream("purchases.bin");
            ObjectInputStream readPurchases = new ObjectInputStream(purchasesFileIn);
            try {
                PurchaseList purchaseList = (PurchaseList) readPurchases.readObject();
                for (int i = 0; i < purchaseList.size(); i++) {
                    System.out.println(purchaseList.get(i));
                }
            } catch (EOFException eof) {
                System.out.println("End of file");
            }
            readPurchases.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, or could not be opened");
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
}