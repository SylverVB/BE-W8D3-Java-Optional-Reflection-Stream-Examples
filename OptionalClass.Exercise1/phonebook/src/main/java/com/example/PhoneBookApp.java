package com.example;

import java.util.Optional;
import java.util.Scanner;

public class PhoneBookApp {

    public static void main(String[] args) {
        // Initialize the phonebook with sample data
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addEntry("Jos de Vos", "016/161616");
        phoneBook.addEntry("Alice Johnson", "017/171717");

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the PhoneBook Application!");
        System.out.println("Available options:");
        System.out.println("1. Add new phone book entry");
        System.out.println("2. Find phone number by name");
        System.out.println("3. Find name by phone number");
        System.out.println("4. Exit");
        
        boolean running = true;
        while (running) {
            System.out.print("Please select an option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    // Add new entry to the phonebook
                    System.out.print("Enter the name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the phone number: ");
                    String newPhone = scanner.nextLine();
                    phoneBook.addEntry(newName, newPhone);
                    System.out.println("New entry added!");
                    break;

                case 2:
                    // Find phone number by name
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    Optional<String> phoneNumber = phoneBook.findPhoneNumberByName(name);
                    if (phoneNumber.isPresent()) {
                        System.out.println("Phone Number: " + phoneNumber.get());
                    } else {
                        System.out.println("Name not found in the phonebook.");
                    }
                    break;

                case 3:
                    // Find name by phone number
                    System.out.print("Enter the phone number: ");
                    String phone = scanner.nextLine();
                    Optional<String> foundName = phoneBook.findNameByPhoneNumber(phone);
                    if (foundName.isPresent()) {
                        System.out.println("Name: " + foundName.get());
                    } else {
                        System.out.println("Phone number not found in the phonebook.");
                    }
                    break;

                case 4:
                    // Exit the application
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}