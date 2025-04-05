# PhoneBook Exercise

## Description

This exercise involves implementing a `PhoneBook` class and testing it using JUnit. The task is to implement two methods in the `PhoneBook` class:

1. **findPhoneNumberByName**: This method finds a phone number based on the provided name and returns an `Optional<String>`.
2. **findNameByPhoneNumber**: This method finds a name based on the provided phone number and returns an `Optional<String>`.

The provided test file verifies the correctness of the implementation.

---

## Partial Code

### `PhoneBook` Class

The `PhoneBook` class includes predefined entries and the methods to implement.

```java
package optionals;

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

    PhoneBook() { }

    public HashMap<String, String> getPhoneBookEntries() {
        return phoneBookEntries;
    }

    public void addEntry(String name, String phoneNumber) {
        phoneBookEntries.put(name, phoneNumber);
    }

    public Optional<String> findPhoneNumberByName(String name) {
        return null;
    }

    public Optional<String> findNameByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public String toString() {
        System.out.println("Hello from PhoneBook's toString method");
        return "PhoneBook{" +
                "phoneBookEntries=" + phoneBookEntries +
                '}';
    }
}
```

---

## Test Cases

The functionality of the `PhoneBook` methods will be validated using JUnit tests.

### Test File

```java
package optionals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PhoneBook phoneBook = new PhoneBook();

    @Test
    public void findPhoneNumberByName() {
        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("Jos de Vos");

        assertThat(phoneNumber.get()).isEqualTo("016/161616");
    }

    @Test
    public void findPhoneNumberByName_NotFound() {
        expectedException.expect(NoSuchElementException.class);

        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("Jos de Voss");

        phoneNumber.get();
    }

    @Test
    public void findNameByPhoneNumber() {
        Optional<String> name = phoneBook.findNameByPhoneNumber("016/161616");

        assertThat(name.get()).isEqualTo("Jos de Vos");
    }

    @Test
    public void findNameByPhoneNumber_NotFound() {
        expectedException.expect(NoSuchElementException.class);

        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("016/161619");

        phoneNumber.get();
    }
}
```

---

## How to Run

1. **Implement the missing methods** (`findPhoneNumberByName` and `findNameByPhoneNumber`) in the `PhoneBook` class.
2. **Compile the classes**:
   - `PhoneBook`
   - `PhoneBookTest`
3. **Run the tests** using JUnit to ensure correctness.

---

## Notes

- The `PhoneBookApp.java` file was added as part of your exercise to create an interactive program that allows you to manage phone book entries via the command line.
- The focus of this exercise is to implement the core `PhoneBook` methods and test them using the provided test cases.