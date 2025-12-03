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

                        residentList.addResident(new Resident(1, "Green", "Bob", 9999999));
                        residentList.addResident(new Resident(2, "Green", "Smith", 0));
                        residentList.addResident(new Resident(3, "Charlie", "Brown", 0));
                        residentList.addResident(new Resident(4, "Diana", "White", 0));

                        out.writeObject(residentList);
                        System.out.println("Success writing residents");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // write taskslist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("tasks.bin"))) {
                        TasksList tasksList = new TasksList();

                        tasksList.addTask(new GreenActions("Recycle paper", "green_action", 10));
                        tasksList.addTask(new GreenActions("Plant a tree", "green_action", 50));
                        tasksList.addTask(new CommunityTasks("Park cleanup", "community_task", 20));
                        tasksList.addTask(new CommunityTasks("Help neighbor", "community_task", 15));

                        out.writeObject(tasksList);
                        System.out.println("Success writing tasks");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // write tradelist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("trades.bin"))) {
                        TradeList tradeList = new TradeList();

                        tradeList.addTrade(new Trade("Pot for 5 apples", "xd", 20));

                        out.writeObject(tradeList);
                        System.out.println("Success writing trades");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Done writing");
        }
}