# Java Stream API

The **Stream API** in Java provides a set of methods that allow you to perform functional-style operations on sequences of elements, such as collections, arrays, or I/O channels. Introduced in Java 8, it is part of the `java.util.stream` package and revolutionized how data is processed in collections.

---

## What is a Stream?

A Stream is a sequence of elements that supports **functional-style operations** such as filtering, mapping, and reducing. Streams can process data declaratively and provide a flexible way to create complex data pipelines.

### Key Characteristics:

1. **Non-Storage**: Streams do not store data; they process data from a source (e.g., a collection or array).
2. **Lazy Execution**: Intermediate operations are lazy and executed only when a terminal operation is invoked.
3. **Functional**: You can chain multiple operations for processing data.
4. **Immutable**: Streams do not modify the original data source.

---

## Types of Streams

### 1. **Sequential Stream**
   Processes elements one at a time in a single thread.

### 2. **Parallel Stream**
   Utilizes multiple threads to process elements in parallel for better performance on large datasets.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Sequential Stream
numbers.stream().forEach(System.out::println);

// Parallel Stream
numbers.parallelStream().forEach(System.out::println);
```

---

## Stream Operations

Streams support two types of operations:

### 1. **Intermediate Operations**
   These return a new stream, allowing chaining of multiple operations. They are lazy and executed only when a terminal operation is performed.

   **Examples**: `filter()`, `map()`, `distinct()`, `sorted()`, `limit()`, `skip()`.

### 2. **Terminal Operations**
   These trigger the execution of the entire stream pipeline and produce a final result.

   **Examples**: `collect()`, `forEach()`, `reduce()`, `count()`, `findFirst()`, `findAny()`.

---

## Common Stream Methods

### 1. `filter()`

Filters elements based on a predicate.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
List<String> filteredNames = names.stream()
                                  .filter(name -> name.startsWith("A"))
                                  .collect(Collectors.toList());
System.out.println(filteredNames);  // Output: [Alice]
```

---

### 2. `map()` and `mapToX()`

Transforms each element by applying a function. `mapToInt()`, `mapToDouble()`, and `mapToLong()` return streams of primitive types.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<Integer> nameLengths = names.stream()
                                 .map(String::length)
                                 .collect(Collectors.toList());
System.out.println(nameLengths);  // Output: [5, 3, 7]
```

---

### 3. `flatMap()`

Flattens a stream of streams into a single stream.

**Example**:
```java
List<List<String>> nested = Arrays.asList(
    Arrays.asList("apple", "banana"),
    Arrays.asList("carrot", "date")
);

List<String> flattened = nested.stream()
                               .flatMap(List::stream)
                               .collect(Collectors.toList());
System.out.println(flattened);  // Output: [apple, banana, carrot, date]
```

---

### 4. `distinct()`

Returns a stream with unique elements.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4);
List<Integer> uniqueNumbers = numbers.stream()
                                     .distinct()
                                     .collect(Collectors.toList());
System.out.println(uniqueNumbers);  // Output: [1, 2, 3, 4]
```

---

### 5. `sorted()`

Sorts elements either in natural order or using a custom comparator.

**Example**:
```java
List<String> names = Arrays.asList("Charlie", "Bob", "Alice");
List<String> sortedNames = names.stream()
                                .sorted()
                                .collect(Collectors.toList());
System.out.println(sortedNames);  // Output: [Alice, Bob, Charlie]
```

---

### 6. `reduce()`

Performs a reduction operation to combine elements into a single result.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
int sum = numbers.stream()
                 .reduce(0, Integer::sum);
System.out.println(sum);  // Output: 10
```

---

### 7. `collect()`

Transforms elements into a collection or a custom result container using `Collectors`.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Set<String> nameSet = names.stream()
                           .collect(Collectors.toSet());
System.out.println(nameSet);  // Output: [Alice, Bob, Charlie]
```

---

### 8. `forEach()`

Performs an action for each element.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream().forEach(System.out::println);
```

---

### 9. `limit()` and `skip()`

- **`limit(n)`**: Returns the first `n` elements.
- **`skip(n)`**: Skips the first `n` elements.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> firstTwo = numbers.stream()
                                .limit(2)
                                .collect(Collectors.toList());
System.out.println(firstTwo);  // Output: [1, 2]
```

---

### 10. `anyMatch()`, `allMatch()`, `noneMatch()`

Checks whether elements match a predicate.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
boolean hasShortName = names.stream().anyMatch(name -> name.length() <= 3);
System.out.println(hasShortName);  // Output: true
```

---

# Reduction Operations

Reduction operations are used to perform a terminal operation on a stream, resulting in a single value by combining elements.

## 1. `reduce()` with Identity and Accumulator

The `reduce()` method combines elements of a stream using an accumulator function and an identity value.

**Syntax**:
```java
T reduce(T identity, BinaryOperator<T> accumulator);
```

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
                 .reduce(0, (a, b) -> a + b);
System.out.println(sum);  // Output: 15
```

## 2. `reduce()` with Accumulator Only

This version returns an `Optional` because the stream may be empty.

**Syntax**:
```java
Optional<T> reduce(BinaryOperator<T> accumulator);
```

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> product = numbers.stream()
                                   .reduce((a, b) -> a * b);
product.ifPresent(System.out::println);  // Output: 120
```

## 3. `reduce()` with Identity, Accumulator, and Combiner

This is useful for parallel streams, where the combiner merges partial results.

**Syntax**:
```java
<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
```

**Example**:
```java
List<String> words = Arrays.asList("hello", "world");
int totalLength = words.stream()
                       .reduce(0, 
                               (length, word) -> length + word.length(),
                               Integer::sum);
System.out.println(totalLength);  // Output: 10
```

---

# Collectors Operations

Collectors are used to transform the elements of a stream into a different data structure or perform aggregate operations.

## 1. Collect to List

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<String> result = names.stream()
                           .collect(Collectors.toList());
System.out.println(result);  // Output: [Alice, Bob, Charlie]
```

## 2. Collect to Set

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Alice", "Charlie");
Set<String> result = names.stream()
                          .collect(Collectors.toSet());
System.out.println(result);  // Output: [Alice, Bob, Charlie]
```

## 3. Collect to Map

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Map<String, Integer> nameLengthMap = names.stream()
                                          .collect(Collectors.toMap(name -> name, name -> name.length()));
System.out.println(nameLengthMap);  // Output: {Alice=5, Bob=3, Charlie=7}
```

## 4. Joining Strings

The `joining()` collector concatenates elements into a single `String`.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
String result = names.stream()
                     .collect(Collectors.joining(", "));
System.out.println(result);  // Output: Alice, Bob, Charlie
```

## 5. Summarizing Data

The `summarizingInt()`, `summarizingDouble()`, and `summarizingLong()` collectors return a summary of statistics.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
IntSummaryStatistics stats = numbers.stream()
                                    .collect(Collectors.summarizingInt(Integer::intValue));
System.out.println(stats);  // Output: IntSummaryStatistics{count=5, sum=15, min=1, average=3.0, max=5}
```

## 6. Grouping by a Property

The `groupingBy()` collector groups elements by a classifier function.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
Map<Character, List<String>> groupedByFirstChar = names.stream()
                                                       .collect(Collectors.groupingBy(name -> name.charAt(0)));
System.out.println(groupedByFirstChar);  // Output: {A=[Alice, Anna], B=[Bob], C=[Charlie]}
```

## 7. Partitioning by a Predicate

The `partitioningBy()` collector partitions elements into two groups based on a predicate.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Map<Boolean, List<Integer>> partitioned = numbers.stream()
                                                 .collect(Collectors.partitioningBy(num -> num % 2 == 0));
System.out.println(partitioned);  
// Output: {false=[1, 3, 5], true=[2, 4]}
```

## 8. Counting Elements

The `counting()` collector counts the number of elements.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
long count = names.stream()
                  .collect(Collectors.counting());
System.out.println(count);  // Output: 3
```

## 9. Reducing with Collectors

The `reducing()` collector performs a reduction as part of the collect operation.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
                 .collect(Collectors.reducing(0, (a, b) -> a + b));
System.out.println(sum);  // Output: 15
```

## 10. Mapping

The `mapping()` collector applies a mapping function and collects the results using another collector.

**Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<Integer> nameLengths = names.stream()
                                 .collect(Collectors.mapping(String::length, Collectors.toList()));
System.out.println(nameLengths);  // Output: [5, 3, 7]
```

---

By combining **reduction** and **collectors**, developers can perform powerful data processing tasks with the Java Stream API.

---

## Stream API Example: Associate Class

Below is an example implementation of the Stream API using an `Associate` class and a list of `Associate` objects.

### Associate Class
```java
public class Associate {

    private int age;
    private String firstName;
    private String lastName;

    // Constructor
    public Associate(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
```

### Stream API Operations

Let's create a list of `Associate` objects in the main() method and demonstrate common Stream API operations.

```java
List<Associate> associateList = new ArrayList<>();
associateList.add(new Associate(1, "Juan", "Lopez"));
associateList.add(new Associate(2, "Ariel", "Gomez"));
associateList.add(new Associate(3, "Peter", "Alagna"));
associateList.add(new Associate(4, "Bobbert", "Lesley"));

// Below are several examples of working with streams:

/** Iterate **/
System.out.println("Iterating over all list: ");
associateList.stream().forEach((Associate a) -> {
    System.out.println(a.getFirstName());
});

/** Filter **/
String filter = "r";
System.out.println("\nIterating over list with filter (" + filter + "):");
associateList.stream()
    .filter((Associate a) -> new StringBuilder(a.getFirstName()).indexOf(filter) != -1)
    .forEach((Associate a) -> {
        System.out.println(a.getFirstName());
    });

/** Getting Max Age Value **/
int maxAge = associateList.stream()
    .mapToInt(Associate::getAge)
    .max()
    .getAsInt();
System.out.println("\nThe highest age: " + maxAge);

/** Getting Average Age Value **/
double avgAge = associateList.stream()
    .mapToDouble(Associate::getAge)
    .average()
    .getAsDouble();
System.out.println("\nThe average age: " + avgAge);
```

### Explanation:

1. **Iterate**:
   - The `forEach()` terminal operation iterates over the stream and prints the `firstName` of each `Associate`.

2. **Filter**:
   - The `filter()` intermediate operation applies a predicate to include only those `Associate` objects whose `firstName` contains the letter "r".
   - The resulting stream is iterated using `forEach()` to display the filtered names.

3. **Getting Max Age**:
   - The `mapToInt()` intermediate operation extracts the `age` property of each `Associate` and converts it into an `IntStream`.
   - The `max()` terminal operation finds the highest age value.

4. **Getting Average Age**:
   - Similar to `max()`, but here `mapToDouble()` is used to extract the `age` values into a `DoubleStream`.
   - The `average()` terminal operation calculates the mean age value.

---

This example demonstrates how the Stream API enables concise and readable functional-style operations on collections. It showcases the power of streams in performing tasks such as iteration, filtering, and aggregation in an efficient and declarative manner.

## Conclusion

The **Stream API** simplifies and enhances data processing by enabling a **functional programming approach** in Java. By leveraging streams, you can write concise, efficient, and readable data pipelines that handle complex operations with ease.