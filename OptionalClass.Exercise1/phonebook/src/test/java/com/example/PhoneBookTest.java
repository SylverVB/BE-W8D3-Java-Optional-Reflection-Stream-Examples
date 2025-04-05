package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookTest {

    private PhoneBook phoneBook = new PhoneBook();

    // Test: Find phone number by name (successful case)
    @Test
    public void findPhoneNumberByName() {
        // Arrange & Act
        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("Jos de Vos");

        // Assert
        assertThat(phoneNumber.isPresent()).isTrue();
        assertThat(phoneNumber.get()).isEqualTo("016/161616");
    }

    // Test: Find phone number by name (not found)
    @Test
    public void findPhoneNumberByName_NotFound() {
        // Act & Assert
        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            phoneBook.findPhoneNumberByName("Nonexistent Name").get();
        });

        assertThat(exception).isNotNull();
    }

    // Test: Find name by phone number (successful case)
    @Test
    public void findNameByPhoneNumber() {
        // Arrange & Act
        Optional<String> name = phoneBook.findNameByPhoneNumber("016/161616");

        // Assert
        assertThat(name.isPresent()).isTrue();
        assertThat(name.get()).isEqualTo("Jos de Vos");
    }

    // Test: Find name by phone number (not found)
    @Test
    public void findNameByPhoneNumber_NotFound() {
        // Act & Assert
        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            phoneBook.findNameByPhoneNumber("000/0000000").get();
        });

        assertThat(exception).isNotNull();
    }
}