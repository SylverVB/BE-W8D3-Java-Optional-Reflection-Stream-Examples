// ## Exercises

// Normally it is up to the programmer to write a `toString()` method for each class one creates. This exercise is about writing a general `toString()` method once and for all. As part of the Reflection API for Java, it is possible to find out which fields exist for a given object, and to get their values. The purpose of this exercise is to make a `toString()` method that gives a printed representation of any object, in such a manner that all fields are printed, and references to other objects are handled as well.

// To solve the exercises, you will need to examine the `java.lang.reflect` API.

// 1. Write a class `ToString` with one static method `String toString(Object o)`. The first version should just return the name of the class the object is an instance of. Write another class, `ToStringTest`, which prints the result of calling `ToString.toString("Hans")`.
// 2. Extend the `toString` method. This time it should find out which fields exist in the object, and return a string of the format `classname{fieldName1, fieldName2,...,fieldNameN}`. When this works, make sure you do not print out a superfluous comma just before the closing brace.
// 3. Extend the `toString` method, so that each field is printed in the form `fieldName: fieldType`.
// 4. We do not want static fields to be included in the printout. Make sure no static fields are printed. Keep testing the method using the `ToStringTest` method.
// 5. Extend the method to print out the values of each field using Java's built-in `toString` method. The format for each field should now be `fieldName: fieldType = value`. Note, the value of a private field can be read after you use the `setAccessible(true)` method that fields inherit from `AccessibleObject`.
// 6. A field might be an Array. If it is, write each value in the array as `[val1, val2, val3,..., valN]`. Optional extra: If the array has more than 15 elements, only the first 15 should be printed, and the rest should be printed as `...`. Hint on retrieving values from an Array: Try looking at the documentation of the `Array` class in the API.

package ReflectionAPI;

import java.lang.reflect.*;
import java.util.Arrays;

public class ToString {

    // First version: Just return class name
    public static String toString(Object o) {
        return o.getClass().getName();
    }

    // Second version: Return field names
    public static String toStringWithFields(Object o) {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = o.getClass();
        result.append(clazz.getName()).append("{");
        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            if (!first) result.append(", ");
            result.append(field.getName());
            first = false;
        }
        result.append("}");
        return result.toString();
    }

    // Third version: Include field names and types
    public static String toStringWithFieldTypes(Object o) {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = o.getClass();
        result.append(clazz.getName()).append("{");
        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            if (!first) result.append(", ");
            result.append(field.getName()).append(": ").append(field.getType().getName());
            first = false;
        }
        result.append("}");
        return result.toString();
    }

    // Fourth version: Include field values
    public static String toStringWithValues(Object o) {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = o.getClass();
        result.append(clazz.getName()).append("{");
        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true); // Make sure the private fields are accessible
            try {
                Object value = field.get(o);
                if (!first) result.append(", ");
                result.append(field.getName())
                        .append(": ")
                        .append(field.getType().getName())
                        .append(" = ")
                        .append(value != null ? value.toString() : "null");
                first = false;
            } catch (IllegalAccessException e) {
                result.append(field.getName()).append(": inaccessible");
            }
        }
        result.append("}");
        return result.toString();
    }

    // Fifth version: Handle arrays
    public static String toStringWithArrayHandling(Object o) {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = o.getClass();
        result.append(clazz.getName()).append("{");
        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true); // Make sure the private fields are accessible
            try {
                Object value = field.get(o);
                if (value != null && value.getClass().isArray()) {
                    Object[] array = (Object[]) value;
                    result.append(field.getName()).append(": ")
                          .append(field.getType().getName())
                          .append(" = [")
                          .append(Arrays.toString(Arrays.copyOfRange(array, 0, Math.min(array.length, 15))));
                    if (array.length > 15) result.append(", ...");
                    result.append("]");
                } else {
                    if (!first) result.append(", ");
                    result.append(field.getName())
                          .append(": ")
                          .append(field.getType().getName())
                          .append(" = ")
                          .append(value != null ? value.toString() : "null");
                }
                first = false;
            } catch (IllegalAccessException e) {
                result.append(field.getName()).append(": inaccessible");
            }
        }
        result.append("}");
        return result.toString();
    }
}

class ToStringTest {
    public static void main(String[] args) {
        // Create a test object
        Person p = new Person("Hans", 25, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

        // Test different versions and print their results
        System.out.println("First version (class name):");
        System.out.println(ToString.toString(p)); // Output: Person
        System.out.println();

        System.out.println("Second version (field names):");
        System.out.println(ToString.toStringWithFields(p)); // Output: Person{name, age, numbers}
        System.out.println();

        System.out.println("Third version (field names and types):");
        System.out.println(ToString.toStringWithFieldTypes(p)); // Output: Person{name: java.lang.String, age: int, numbers: [I}
        System.out.println();

        System.out.println("Fourth version (field names, types, and values):");
        System.out.println(ToString.toStringWithValues(p)); // Output: Person{name: java.lang.String = Hans, age: int = 25, numbers: [I = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]}
        System.out.println();

        System.out.println("Fifth version (array handling):");
        System.out.println(ToString.toStringWithArrayHandling(p)); // Output: Person{name: java.lang.String = Hans, age: int = 25, numbers: [I = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16], ...}
    }
}

class Person {
    private String name;
    private int age;
    private int[] numbers;

    // Constructor
    public Person(String name, int age, int[] numbers) {
        this.name = name;
        this.age = age;
        this.numbers = numbers;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Getter for numbers
    public int[] getNumbers() {
        return numbers;
    }
}