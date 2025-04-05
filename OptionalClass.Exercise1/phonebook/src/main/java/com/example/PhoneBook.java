package com.example;

import java.util.*;

public class PhoneBook {

    private static final HashMap<String, String> PHONE_NUMBERS = new HashMap<String, String>() {
        {
            put("Ted Striker", "5551212");
            put("Roger Murdock", "3879812");
            put("Elaine Dickinson", "8675309");
            put("Jos de Vos", "016/161616");
        }
    };

    private HashMap<String, String> phoneBookEntries = PHONE_NUMBERS;

    // Default constructor
    PhoneBook() { }

    // Get the phone book entries
    public HashMap<String, String> getPhoneBookEntries() {
        return phoneBookEntries;
    }

    // Find phone number by name
    public Optional<String> findPhoneNumberByName(String name) {
        // Look up the name in the phoneBookEntries map and wrap it in an Optional
        return Optional.ofNullable(phoneBookEntries.get(name));
    }

    // Find name by phone number
    public Optional<String> findNameByPhoneNumber(String phoneNumber) {
        // Iterate over the map entries to find a match
        for (Map.Entry<String, String> entry : phoneBookEntries.entrySet()) {
            if (entry.getValue().equals(phoneNumber)) {
                return Optional.of(entry.getKey());  // Return the name if found
            }
        }
        // Return an empty Optional if no match is found
        return Optional.empty();
    }

    // Add an entry to the phone book
    public void addEntry(String name, String phoneNumber) {
        phoneBookEntries.put(name, phoneNumber);
    }

    // Override toString method to return a readable string for PhoneBook object
    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBookEntries=" + phoneBookEntries +
                '}';
    }
}