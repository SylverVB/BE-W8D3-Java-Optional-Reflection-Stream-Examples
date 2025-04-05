Here is a comprehensive list of **Arrays** and **List** methods in Java that are commonly used to solve coding challenges. These methods will help you manipulate and analyze data structures effectively.

---

## **Array Methods**
### Arrays are fixed in size and provide several utility methods through the `java.util.Arrays` class.

### **Initialization**
1. `Arrays.fill(array, value)`  
   Fills the entire array with the specified value.

2. `Arrays.fill(array, start, end, value)`  
   Fills a range in the array with the specified value.

3. `new int[]{1, 2, 3}`  
   Creates an array with specified elements.

---

### **Sorting**
4. `Arrays.sort(array)`  
   Sorts the array in ascending order.

5. `Arrays.sort(array, Collections.reverseOrder())`  
   Sorts the array in descending order (only for `Integer[]`, not `int[]`).

6. `Arrays.sort(array, start, end)`  
   Sorts a subarray.

---

### **Search**
7. `Arrays.binarySearch(array, value)`  
   Performs binary search. The array must be sorted.

---

### **Conversion**
8. `Arrays.toString(array)`  
   Returns a string representation of the array.

9. `Arrays.asList(array)`  
   Converts the array to a `List`. Note: Changes to the list affect the original array.

10. `Arrays.stream(array)`  
    Converts the array to a stream (useful for applying functional operations).

---

### **Comparison**
11. `Arrays.equals(array1, array2)`  
    Compares two arrays for equality.

12. `Arrays.compare(array1, array2)`  
    Compares two arrays lexicographically.

13. `Arrays.deepEquals(array1, array2)`  
    Compares nested arrays for equality.

---

### **Copy**
14. `Arrays.copyOf(array, newLength)`  
    Copies the array to a new array with the specified length.

15. `Arrays.copyOfRange(array, start, end)`  
    Copies a subarray.

---

### **Other**
16. `Arrays.hashCode(array)`  
    Returns the hash code of the array.

17. `Arrays.stream(array).sum()`  
    Sums all elements in the array.

18. `Arrays.stream(array).average().getAsDouble()`  
    Calculates the average of the elements.

19. `Arrays.stream(array).distinct().toArray()`  
    Returns an array with duplicate elements removed.

---

## **List Methods**
### Lists are part of the `java.util` package and include classes like `ArrayList`, `LinkedList`, etc.

---

### **Initialization**
1. `new ArrayList<>(Arrays.asList(elements))`  
   Creates a `List` from an array.

2. `List.of(elements)`  
   Creates an immutable list (Java 9+).

3. `List.copyOf(list)`  
   Returns an unmodifiable copy of the list.

---

### **Add/Remove**
4. `list.add(value)`  
   Adds a value to the end of the list.

5. `list.add(index, value)`  
   Adds a value at the specified index.

6. `list.remove(index)`  
   Removes the element at the specified index.

7. `list.remove(Object)`  
   Removes the first occurrence of the specified value.

8. `list.clear()`  
   Removes all elements from the list.

---

### **Access**
9. `list.get(index)`  
   Retrieves the element at the specified index.

10. `list.set(index, value)`  
    Replaces the element at the specified index.

---

### **Search**
11. `list.indexOf(value)`  
    Returns the index of the first occurrence of the value.

12. `list.lastIndexOf(value)`  
    Returns the index of the last occurrence of the value.

---

### **Sorting**
13. `Collections.sort(list)`  
    Sorts the list in ascending order.

14. `list.sort(Comparator.reverseOrder())`  
    Sorts the list in descending order.

---

### **Conversion**
15. `list.toArray()`  
    Converts the list to an array.

16. `String.join(delimiter, list)`  
    Joins list elements into a single string with a delimiter.

---

### **Sublist**
17. `list.subList(start, end)`  
    Returns a sublist view of the list.

---

### **Stream Operations**
18. `list.stream()`  
    Converts the list to a stream for functional operations.

19. `list.stream().filter(condition).collect(Collectors.toList())`  
    Filters the list and collects the result.

20. `list.stream().map(function).collect(Collectors.toList())`  
    Applies a function to all elements and collects the result.

---

### **Comparison**
21. `list.equals(otherList)`  
    Checks if two lists are equal.

22. `list.contains(value)`  
    Returns true if the list contains the value.

23. `list.containsAll(collection)`  
    Checks if the list contains all elements of another collection.

---

### **Other**
24. `list.isEmpty()`  
    Checks if the list is empty.

25. `list.size()`  
    Returns the size of the list.

26. `list.forEach(action)`  
    Performs an action for each element in the list.

27. `list.retainAll(collection)`  
    Retains only the elements in the list that are also in the specified collection.

28. `list.removeAll(collection)`  
    Removes all elements in the list that are in the specified collection.

29. `list.replaceAll(function)`  
    Replaces all elements in the list with the result of applying a function.