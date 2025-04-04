# **The Optional Class in Java**

The `Optional` class in Java is a container object that may or may not contain a non-null value. Introduced in Java 8, it helps handle cases where methods might return `null` values, encouraging explicit handling of absent values. This reduces the likelihood of encountering unexpected `NullPointerExceptions` and makes the code more expressive.

---

## **Optional Methods with Examples**

### **1. `of(value)`**
Creates an `Optional` containing the specified non-null value. Throws a `NullPointerException` if the value is `null`.

```java
Optional<String> optional = Optional.of("biscuits");
System.out.println(optional.get()); // Output: biscuits
```

---

### **2. `ofNullable(value)`**
Creates an `Optional` containing the specified value, which may be `null`. If the value is `null`, it returns an empty `Optional`.

```java
Optional<String> optional = Optional.ofNullable(null);
System.out.println(optional.isPresent()); // Output: false
```

---

### **3. `empty()`**
Returns an empty `Optional` instance, representing the absence of a value.

```java
Optional<String> optional = Optional.empty();
System.out.println(optional.isPresent()); // Output: false
```

---

### **4. `isPresent()`**
Returns `true` if the `Optional` contains a non-null value, otherwise `false`.

```java
Optional<String> optional = Optional.of("example");
System.out.println(optional.isPresent()); // Output: true
```

---

### **5. `ifPresent(consumer)`**
If a value is present, performs the given action with the value. Otherwise, does nothing.

```java
Optional<String> optional = Optional.of("Hello");
optional.ifPresent(value -> System.out.println(value)); // Output: Hello
```

---

### **6. `get()`**
If a value is present, returns the value. Otherwise, throws a `NoSuchElementException`.

```java
Optional<String> optional = Optional.of("Value");
System.out.println(optional.get()); // Output: Value
```

---

### **7. `orElse(other)`**
If a value is present, returns the value. Otherwise, returns the specified other value.

```java
Optional<String> optional = Optional.empty();
System.out.println(optional.orElse("Default Value")); // Output: Default Value
```

---

### **8. `orElseGet(Supplier)`**
If a value is present, returns the value. Otherwise, returns the result produced by the specified `Supplier`.

```java
Optional<String> optional = Optional.empty();
System.out.println(optional.orElseGet(() -> "Generated Value")); // Output: Generated Value
```

---

### **9. `orElseThrow(Supplier)`**
If a value is present, returns the value. Otherwise, throws an exception created by the provided `Supplier`.

```java
Optional<String> optional = Optional.empty();
optional.orElseThrow(() -> new RuntimeException("Value not present")); // Throws RuntimeException
```

---

### **10. `map(Function)`**
If a value is present, applies the given mapping function to it and returns an `Optional` containing the result. Otherwise, returns an empty `Optional`.

```java
Optional<String> optional = Optional.of("java");
Optional<String> upper = optional.map(String::toUpperCase);
upper.ifPresent(System.out::println); // Output: JAVA
```

---

### **11. `flatMap(Function)`**
If a value is present, applies the given mapping function to it, which must return an `Optional`, and returns the result. Otherwise, returns an empty `Optional`.

```java
Optional<String> optional = Optional.of("java");
Optional<String> upper = optional.flatMap(value -> Optional.of(value.toUpperCase()));
upper.ifPresent(System.out::println); // Output: JAVA
```

---

### **12. `filter(Predicate)`**
If a value is present and matches the given predicate, returns an `Optional` containing the value. Otherwise, returns an empty `Optional`.

```java
Optional<String> optional = Optional.of("java");
Optional<String> filtered = optional.filter(value -> value.length() > 3);
filtered.ifPresent(System.out::println); // Output: java
```

---

## **When to Use `orElse()` vs `orElseGet()`**

- Use **`orElse()`** for simple or already computed values:
  ```java
  Optional<String> optional = Optional.empty();
  System.out.println(optional.orElse("Default Value")); // Always computes "Default Value".
  ```

- Use **`orElseGet()`** for expensive computations or lazily evaluated values:
  ```java
  Optional<String> optional = Optional.empty();
  System.out.println(optional.orElseGet(() -> "Generated Value")); // Computed only when needed.
  ```

---

## **When to Use `map()` vs `flatMap()`**

- Use **`map()`** for transformations that return non-optional values:
  ```java
  Optional<String> optional = Optional.of("java");
  optional.map(String::toUpperCase).ifPresent(System.out::println); // Output: JAVA
  ```

- Use **`flatMap()`** for transformations that return `Optional` values:
  ```java
  Optional<String> optional = Optional.of("java");
  optional.flatMap(value -> Optional.of(value.toUpperCase())).ifPresent(System.out::println); // Output: JAVA
  ```

### **Clarifying the Differences Between orElse() vs orElseGet() and map() vs flatMap()**

It might seem like `orElse()` and `orElseGet()`, or `map()` and `flatMap()`, behave the same way in the examples above. However, their differences become apparent in specific scenarios involving **computation costs** and **nested Optionals**.

---

### **1. orElse() vs orElseGet()**

#### **Key Difference: Eager vs Lazy Evaluation**
- **`orElse()`** always computes the fallback value, regardless of whether the `Optional` contains a value.
- **`orElseGet()`** computes the fallback value only if the `Optional` is empty. This is **lazy evaluation**, which can save computation costs.

#### **Why It Matters?**

```java
public class Example {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("java");

        System.out.println(optional.orElse(getFallback())); // Always calls getFallback(), even though it's not used.
        System.out.println(optional.orElseGet(() -> getFallback())); // Does not call getFallback() if optional is present.
    }

    public static String getFallback() {
        System.out.println("Computing fallback...");
        return "Default Value";
    }
}
```

**Output with `orElse()`**:
```plaintext
Computing fallback...
java
```

**Output with `orElseGet()`**:
```plaintext
java
```

**Explanation**:
- `orElse()` calls `getFallback()` even though the `Optional` already contains a value (`"java"`).
- `orElseGet()` avoids calling `getFallback()` because the `Optional` is not empty.

#### **When to Use?**
- Use **`orElse()`** for simple constants or values that are already computed.
- Use **`orElseGet()`** for expensive computations or when the fallback value requires additional processing.

---

### **2. map() vs flatMap()**

#### **Key Difference: Handling Nested Optionals**
- **`map()`** is used when the transformation function returns a **non-Optional** value. The result remains wrapped in an `Optional`.
- **`flatMap()`** is used when the transformation function returns an **Optional**. It "flattens" the nested Optional to avoid `Optional<Optional<T>>`.

#### **Why It Matters?**

```java
Optional<String> optional = Optional.of("java");

// Using map()
Optional<Optional<String>> resultWithMap = optional.map(value -> Optional.of(value.toUpperCase()));
System.out.println(resultWithMap); // Output: Optional[Optional[JAVA]]

// Using flatMap()
Optional<String> resultWithFlatMap = optional.flatMap(value -> Optional.of(value.toUpperCase()));
System.out.println(resultWithFlatMap); // Output: Optional[JAVA]
```

#### **When to Use?**
- Use **`map()`** for transformations that return a regular value (non-Optional):
  ```java
  Optional<String> optional = Optional.of("java");
  optional.map(String::toUpperCase).ifPresent(System.out::println); // Output: JAVA
  ```

- Use **`flatMap()`** for transformations that return an `Optional`:
  ```java
  Optional<String> optional = Optional.of("java");
  optional.flatMap(value -> Optional.of(value.toUpperCase())).ifPresent(System.out::println); // Output: JAVA
  ```

---

### **Summary**
- **`orElse()`** computes its argument eagerly, even if the value is present, while **`orElseGet()`** computes the fallback lazily.
- **`map()`** is for simple transformations returning a non-Optional value, while **`flatMap()`** avoids nested Optionals when the transformation itself returns an `Optional`.

The differences may seem subtle, but understanding these nuances can help you write more efficient and cleaner code, especially when dealing with expensive computations or nested Optionals.

---

### Real World Application

The Java `Optional` class provides a way to represent values that may or may not be present. While it might not seem immediately obvious how this class could be used in real-world applications, there are several scenarios where it can be beneficial:

1. **API Design**:  
   `Optional` can be used in the design of APIs to clearly communicate to users whether a method can return `null` or not. By returning `Optional` instead of `null`, API designers can force users to explicitly handle the case where the value might be absent, improving the clarity and reliability of the API.

2. **Stream Processing**:  
   `Optional` integrates well with Java Streams, allowing developers to process streams of data containing optional values. This can be useful when dealing with collections where some elements might be `null` or absent.

3. **Configuration Settings**:  
   `Optional` can be used to represent optional configuration settings or parameters. For example, a method that reads configuration settings from a file could return an `Optional` containing the value of a specific setting, or an empty `Optional` if the setting is not present.

---

### Implementation Examples

#### Example 1:  
```java
import java.util.Optional;

class Rev {
    public static void main(String[] args) {
        String[] str = new String[5];
        str[2] = "hello world";

        Optional<String> empty = Optional.empty();
        System.out.println(empty); // Output: Optional.empty

        Optional<String> value = Optional.of(str[2]);
        System.out.println(value); // Output: Optional[hello world]
    }
}
```

#### Example 2:  
```java
import java.util.Optional;

class Rev {
    public static void main(String[] args) {
        String[] str = new String[5];
        str[2] = "hello world";

        Optional<String> value = Optional.of(str[2]);

        System.out.println(value.get()); // Output: hello world
        System.out.println(value.hashCode()); // Output: Hash code of value
        System.out.println(value.isPresent()); // Output: true
    }
}
```