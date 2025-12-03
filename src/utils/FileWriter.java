package utils;

import model.*;
import java.util.*;
import java.io.*;
import java.time.LocalTime;

public class FileWriter {
        public static void main(String[] args) {
                // write residentlist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("customers.bin"))) {
                        ResidentList residentList = new ResidentList();
                        ArrayList<Resident> residents = new ArrayList<>();
                        residents.add(new Resident(1, "Green", "Bob", 9999999));
                        residents.add(new Resident(2, "Green", "Smith", 0));
                        residents.add(new Resident(3, "Charlie", "Brown", 0));
                        residents.add(new Resident(4, "Diana", "White", 0));

                        for (Resident resident : residents) {
                                residentList.addResident(resident);
                        }

                        out.writeObject(residentList);
                        System.out.println("Success writing residents");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // write taskslist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("tasks.bin"))) {
                        TasksList tasksList = new TasksList();
                        ArrayList<Task> tasks = new ArrayList<>();
                        tasks.add(new Task("Clean the house", "green_action"));
                        tasks.add(new Task("Ride a bike", "green_action"));
                        tasks.add(new Task("Garden duties", "community_task"));

                        for (Task task : tasks) {
                                tasksList.addTask(task);
                        }

                        out.writeObject(tasksList);
                        System.out.println("Success writing tasks");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // write tradelist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("trades.bin"))) {
                        TradeList tradeList = new TradeList();
                        ArrayList<Trade> trades = new ArrayList<>();
                        trades.add(new Trade(1, "Bob", "Smith", "Book", 5));
                        trades.add(new Trade(2, "Charlie", "Diana", "Game", 2));

                        for (Trade trade : trades) {
                                tradeList.addTrade(trade);
                        }

                        out.writeObject(tradeList);
                        System.out.println("Success writing trades");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Done writing");
        }
}