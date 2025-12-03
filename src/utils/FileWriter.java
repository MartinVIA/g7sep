package utils;

import model.*;
import java.util.*;
import java.io.*;
import java.time.LocalTime;

public class FileWriter {
        public static void main(String[] args) {
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
                        System.out.println("Success");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("pets.bin"))) {
                        PetList petList = new PetList();
                        Pet[] pets = new Pet[20];

                        pets[0] = new Dog("Buddy", 5, "Brown", 'M', "Friendly", 1500,
                                        "Golden Retriever", "Golden Paws");
                        pets[1] = new Dog("Daisy", 4, "White", 'F', "Cute", 800, "Poodle",
                                        "Poodle Palace");
                        pets[2] = new Dog("Max", 6, "Black", 'M', "Loyal", 700, "Labrador",
                                        "Labrador Love");
                        pets[3] = new Dog("Lucy", 3, "Brown", 'F', "Playful", 600, "Beagle",
                                        "Beagle Buddies");
                        pets[4] = new Dog("Charlie", 7, "Black", 'M', "Energetic", 1700,
                                        "German Shepherd", "German Guard");
                        pets[5] = new Cat("Whiskers", 3, "White", 'F', "Playful", 1000, "Siamese",
                                        "Siamese Cats");
                        pets[6] = new Cat("Mittens", 2, "Black", 'F', "Cuddly", 4000, "Persian",
                                        "Persian Paws");
                        pets[7] = new Cat("Tiger", 4, "Orange", 'M', "Curious", 9000, "Savannah",
                                        "Savannah Breeders");
                        pets[8] = new Cat("Smokey", 5, "Grey", 'M', "Lazy", 1700, "Chartreux",
                                        "Chartreux Cats");
                        pets[9] = new Cat("Luna", 1, "White", 'F', "Independent", 17500,
                                        "Ragdoll", "Ragdoll Royalty");
                        pets[10] = new Bird("Polly", 2, "Green", 'F', "Talkative", 40, "Seeds",
                                        "Budgerigar");
                        pets[11] = new Bird("Apollo", 2, "Gray", 'M', "eloquent", 80,
                                        "pistachios", "Parrot");
                        pets[12] = new Bird("Zeus", 1, "Yellow", 'M', "Loud", 50,
                                        "Sunflower seeds", "Budgerigar");
                        pets[13] = new Fish("Bubbles", 1, "Orange", 'M', "Shy", 20, "Freshwater",
                                        false, "Goldfish");
                        pets[14] = new Fish("Nemo", 1, "Orange", 'M', "Adventurous", 50,
                                        "Saltwater", true, "Clownfish");
                        pets[15] = new Fish("Dory", 1, "Blue", 'F', "Forgetful", 80, "Saltwater",
                                        true, "Regal Tang");
                        pets[16] = new Rodent("Nibbles", 1, "Grey", 'M', "Curious", 50, false,
                                        "Hamster");
                        pets[17] = new Rodent("Squeaky", 1, "White", 'F', "Loud", 10, true,
                                        "Rat");
                        pets[18] = new Rodent("Fluffy", 1, "Brown", 'F', "Soft", 300, false,
                                        "Chinchilla");
                        pets[19] = new Various("Maturin", 100, "Green", 'M', "Wise", 1000,
                                        "Turtle");

                        for (Pet pet : pets) {
                                petList.add(pet);
                        }

                        out.writeObject(petList);
                        System.out.println("Pets serialized successfully");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("bookings.bin"))) {
                        KennelList kennelList = new KennelList();
                        Customer[] customers = new Customer[10];
                        Pet[] pets = new Pet[20];

                        customers[0] = new Customer("John", "Doe", "12345678",
                                        "john.doe@mail.com");
                        customers[1] = new Customer("Jane", "Smith", "87654321",
                                        "jane.smith@mail.com");
                        customers[2] = new Customer("James", "Brown", "23456789",
                                        "james.brown@mail.com");
                        customers[3] = new Customer("Emily", "Johnson", "98765432",
                                        "emily.johnson@mail.com");
                        customers[4] = new Customer("Michael", "Williams", "34567890",
                                        "michael.williams@mail.com");
                        customers[5] = new Customer("Sarah", "Taylor", "45678901",
                                        "sarah.taylor@mail.com");
                        customers[6] = new Customer("David", "Miller", "56789012",
                                        "david.miller@mail.com");
                        customers[7] = new Customer("Emma", "Davis", "67890123",
                                        "emma.davis@mail.com");
                        customers[8] = new Customer("Daniel", "Wilson", "78901234",
                                        "daniel.wilson@mail.com");
                        customers[9] = new Customer("Olivia", "Martinez", "89012345",
                                        "olivia.martinez@mail.com");

                        pets[0] = new Dog("Apple", 5, "Beige", 'M', "Friendly",
                                        "Golden Retriever", "Golden Paws");
                        pets[1] = new Dog("Duchess", 4, "White", 'F', "Cute", "Poodle",
                                        "Poodle Palace");
                        pets[2] = new Dog("Mura", 6, "Black", 'F', "Loyal", "Labrador",
                                        "Labrador Love");
                        pets[3] = new Dog("Petal", 3, "Brown", 'F', "Playful", "Beagle",
                                        "Beagle Buddies");
                        pets[4] = new Dog("Tessy", 7, "Black", 'M', "Energetic",
                                        "German Shepherd", "German Guard");
                        pets[5] = new Cat("Mochi", 3, "White", 'F', "Playful", "Ragdoll",
                                        "Ragdoll Cats");
                        pets[6] = new Cat("Void", 2, "Black", 'M', "Cuddly", "European",
                                        "European Cats 4 Sale");
                        pets[7] = new Cat("Thor", 4, "Brown", 'M', "Curious", "Maine Coon",
                                        "The Maine Coons");
                        pets[8] = new Cat("Pufi", 5, "Grey", 'M', "Cuddly", "Norwegian Forest",
                                        "Forest Cats");
                        pets[9] = new Cat("Missy", 1, "White", 'F', "Independent", "Ragdoll",
                                        "Ragdoll Royalty");
                        pets[10] = new Bird("Rafael", 2, "Grey", 'M', "Talkative", "Seeds",
                                        "Budgerigar");
                        pets[11] = new Bird("Blu", 2, "Blue", 'M', "adventurous", "nuts",
                                        "Parrot");
                        pets[12] = new Bird("Jewel", 1, "White", 'F', "Loud", "Sunflower seeds",
                                        "Budgerigar");

                        Booking[] bookings = new Booking[10];
                        bookings[0] = new Booking(customers[0], pets[0], new MyDate(1, 10, 2024),
                                        new MyDate(10, 10, 2024));
                        bookings[1] = new Booking(customers[1], pets[1], new MyDate(2, 12, 2024),
                                        new MyDate(24, 12, 2024));
                        bookings[2] = new Booking(customers[2], pets[2], new MyDate(3, 1, 2024),
                                        new MyDate(10, 1, 2024));
                        bookings[3] = new Booking(customers[3], pets[3], new MyDate(4, 12, 2024),
                                        new MyDate(14, 12, 2024));
                        bookings[4] = new Booking(customers[4], pets[5], new MyDate(21, 11, 2024),
                                        new MyDate(30, 11, 2024));
                        bookings[5] = new Booking(customers[5], pets[6], new MyDate(5, 3, 2024),
                                        new MyDate(15, 3, 2024));
                        bookings[6] = new Booking(customers[6], pets[7], new MyDate(12, 12, 2024),
                                        new MyDate(19, 12, 2024));
                        bookings[7] = new Booking(customers[7], pets[8], new MyDate(6, 11, 2024),
                                        new MyDate(16, 11, 2024));
                        bookings[8] = new Booking(customers[8], pets[10],
                                        new MyDate(15, 12, 2024), new MyDate(20, 12, 2024));
                        bookings[9] = new Booking(customers[9], pets[11], new MyDate(7, 1, 2024),
                                        new MyDate(25, 1, 2024));

                        for (Booking booking : bookings) {
                                kennelList.add(booking);
                        }

                        out.writeObject(kennelList);
                        System.out.println("Bookings serialized successfully");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("purchases.bin")))

                {
                        PurchaseList purchaseList = new PurchaseList();
                        Customer[] customers = new Customer[10];
                        Pet[] pets = new Pet[20];

                        customers[0] = new Customer("John", "Doe", "12345678",
                                        "john.doe@mail.com");
                        customers[1] = new Customer("Jane", "Smith", "87654321",
                                        "jane.smith@mail.com");
                        customers[2] = new Customer("James", "Brown", "23456789",
                                        "james.brown@mail.com");
                        customers[3] = new Customer("Emily", "Johnson", "98765432",
                                        "emily.johnson@mail.com");
                        customers[4] = new Customer("Michael", "Williams", "34567890",
                                        "michael.williams@mail.com");
                        customers[5] = new Customer("Sarah", "Taylor", "45678901",
                                        "sarah.taylor@mail.com");
                        customers[6] = new Customer("David", "Miller", "56789012",
                                        "david.miller@mail.com");
                        customers[7] = new Customer("Emma", "Davis", "67890123",
                                        "emma.davis@mail.com");
                        customers[8] = new Customer("Daniel", "Wilson", "78901234",
                                        "daniel.wilson@mail.com");
                        customers[9] = new Customer("Olivia", "Martinez", "89012345",
                                        "olivia.martinez@mail.com");

                        pets[0] = new Dog("Rex", 4, "Golden", 'M', "brave", 1200,
                                        "Golden Retriever", "Golden Home");
                        pets[1] = new Dog("Bella", 3, "White and Black", 'F', "friendly", 900,
                                        "Dalmatian", "Dalmatian Dream");
                        pets[2] = new Dog("Rocky", 5, "Gray", 'M', "dependable", 1300, "Husky",
                                        "Siberian Haven");
                        pets[3] = new Dog("Molly", 2, "Brown", 'M', "curious", 700,
                                        "Cocker Spaniel", "Cocker Companions");
                        pets[4] = new Dog("Cooper", 6, "Tan", 'M', "protective", 1100,
                                        "Belgian Malinois", "Malinois Masters");
                        pets[5] = new Cat("Shadow", 4, "Black", 'M', "quiet", 3000, "Bombay",
                                        "Bombay Beauty");
                        pets[6] = new Cat("Cleo", 2, "Golden", 'F', "playful", 5000, "Bengal",
                                        "Bengal Kingdom");
                        pets[7] = new Cat("Oliver", 3, "White and Orange", 'M', "affectionate",
                                        8000, "Maine Coon", "Coon Cats");
                        pets[8] = new Cat("Nala", 5, "Cream", 'F', "gentle", 9500,
                                        "Turkish Angora", "Angora Aristocats");
                        pets[9] = new Cat("Simba", 1, "Ginger", 'M', "adventurous", 7000,
                                        "Abyssinian", "Abyssinian Stars");
                        pets[10] = new Bird("Kiwi", 1, "Green", 'F', "friendly", 60, "Fruit mix",
                                        "Lovebird");
                        pets[11] = new Bird("Phoenix", 3, "Red and Yellow", 'M', "vocal", 150,
                                        "Nuts and seeds", "Macaw");
                        pets[12] = new Bird("Sky", 2, "Blue", 'F', "calm", 90, "Sunflower seeds",
                                        "Parakeet");
                        pets[13] = new Bird("Echo", 4, "Gray", 'M', "clever", 120, "Pistachios",
                                        "African Grey");
                        pets[14] = new Bird("Sunny", 2, "Yellow", 'F', "active", 80, "Millet",
                                        "Canary");
                        pets[15] = new Rodent("Chip", 1, "Brown", 'M', "curious", 30, true,
                                        "Hamster Haven");
                        pets[16] = new Rodent("Daisy", 2, "White", 'F', "quiet", 40, false,
                                        "Mouse Mansion");
                        pets[17] = new Rodent("Benny", 3, "Gray", 'M', "energetic", 50, true,
                                        "Guinea Pig Grove");
                        pets[18] = new Fish("Bubbles", 1, "Gold", 'F', "shiny", 20, "Flakes",
                                        true, "Goldfish Paradise");
                        pets[19] = new Fish("Flash", 2, "Silver", 'M', "agile", 25, "Pellets",
                                        true, "Tetra Town");

                        Purchase[] purchases = new Purchase[10];
                        purchases[0] = new Purchase(customers[0], pets[5],
                                        new MyDate(5, 10, 2024), LocalTime.of(9, 30), 30);
                        purchases[1] = new Purchase(customers[1], pets[1],
                                        new MyDate(27, 10, 2024), LocalTime.of(14, 15), 0);
                        purchases[2] = new Purchase(customers[2], pets[2],
                                        new MyDate(26, 9, 2024), LocalTime.of(11, 45), 5);
                        purchases[3] = new Purchase(customers[3], pets[13],
                                        new MyDate(18, 7, 2024), LocalTime.of(16, 30), 0);
                        purchases[4] = new Purchase(customers[4], pets[7],
                                        new MyDate(10, 12, 2024), LocalTime.of(13, 20), 10);
                        purchases[5] = new Purchase(customers[5], pets[6],
                                        new MyDate(17, 7, 2024), LocalTime.of(10, 00), 15);
                        purchases[6] = new Purchase(customers[6], pets[10],
                                        new MyDate(30, 9, 2024), LocalTime.of(15, 45), 0);
                        purchases[7] = new Purchase(customers[7], pets[8],
                                        new MyDate(4, 12, 2024), LocalTime.of(12, 30), 0);
                        purchases[8] = new Purchase(customers[8], pets[15],
                                        new MyDate(5, 10, 2024), LocalTime.of(9, 15), 30);
                        purchases[9] = new Purchase(customers[9], pets[18],
                                        new MyDate(3, 9, 2024), LocalTime.of(16, 0), 0);

                        for (Purchase purchase : purchases) {
                                purchaseList.add(purchase);
                        }

                        out.writeObject(purchaseList);
                        System.out.println("Purchases serialized successfully");
                } catch (Exception e)

                {
                        e.printStackTrace();
                }

                System.out.println("Done writing");
        }
}