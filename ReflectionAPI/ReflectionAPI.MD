# Reflection API in Java

Reflection in Java is a feature that allows a program to examine and manipulate its own structure, behavior, and metadata at runtime. It provides a way to inspect and modify classes, interfaces, fields, methods, and constructors, even if they are private or inaccessible through normal means. Reflection is particularly useful for building generic code, implementing frameworks, and writing debugging tools. The API can be found in the `java.lang.reflect` package.

## Using Reflection

The `java.lang.Class` class represents classes and interfaces in Java. It provides methods to query information about a class, such as its name, superclass, implemented interfaces, fields, methods, and constructors. There are several ways to obtain a Class object:

```java
// Using the .class syntax
Class<?> clazz = MyClass.class;

// Using the getClass() method
Class<?> clazz = obj.getClass();

// Using the Class.forName() method
Class<?> clazz = Class.forName("com.example.MyClass");
```

Once you have a `Class` object, you can inspect its structure using methods such as `getFields()`, `getMethods()`, `getConstructors()`, `getDeclaredFields()`, `getDeclaredMethods()`, and `getDeclaredConstructors()`. These methods return arrays of `Field`, `Method`, or `Constructor` objects, which represent the fields, methods, and constructors of the class, respectively.

The difference between methods that contain the word "declared" in the name and those that don't is that they only include members defined within the class itself, including private members. Methods that do not include the word "declared" include both inherited members and members defined within the class itself, except for private members.

Just as there is a class that represents `Class`, there are classes that represent constructors, fields, and methods. These classes contain methods for accessing metadata about or using these class members.

### Reflection Classes

- **Constructor**: Represents a constructor of a class.
- **Field**: Represents a field (variable) of a class.
- **Method**: Represents a method in a class.
- **Modifier**: Provides constants and methods to inspect access modifiers like `public`, `private`, `static`, etc.

## Resources

- [Java documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/package-summary.html)
- [Java tutorial on Reflection](https://docs.oracle.com/javase/tutorial/reflect/index.html)
- [Oracle article on Reflection](https://www.oracle.com/technical-resources/articles/java/javareflection.html)

## Real World Example

Knowing reflection in Java is important for several reasons:

1. **Introspection**: Reflection enables you to inspect the structure and behavior of classes, interfaces, fields, methods, and constructors at runtime. This introspective capability is valuable for building frameworks, libraries, and tools that need to analyze or manipulate classes dynamically.
   
2. **Dependency Injection and IoC Containers**: Many dependency injection frameworks and Inversion of Control (IoC) containers use reflection to inspect and wire dependencies at runtime. By using reflection, these frameworks can automatically instantiate and inject dependencies into objects based on their annotations or configuration.
   
3. **Testing and Debugging**: Reflection can be helpful in testing and debugging scenarios where you need to access and modify private fields or invoke private methods for testing purposes. It enables you to inspect and manipulate the internal state of objects, which can be useful for writing unit tests or diagnosing issues in production environments.
   
4. **Annotations and Custom Annotations**: Reflection is often used with annotations to process and interpret metadata associated with classes, fields, methods, and other program elements. It allows you to extract and analyze annotation metadata at runtime, enabling powerful runtime behavior based on annotations.
   
5. **Framework and Library Development**: Reflection is frequently employed in the development of frameworks and libraries to provide flexible and extensible behavior. Frameworks can use reflection to dynamically discover and invoke extension points, configure components based on annotations or configuration files, and enforce runtime constraints or policies.

Overall, understanding reflection in Java empowers developers to build more flexible, extensible, and dynamic applications by leveraging the power of introspection and runtime manipulation of classes and objects. While reflection can be a powerful tool, it should be used judiciously due to its potential performance overhead and the complexity it introduces to the codebase.

## Implementation

When using the Reflection API, we use the following three steps:

1. Obtain a `Class` object
2. Get one or more of its class members
3. Manipulate or use the class member

### Obtaining a Class Object

```java
Class<?> classObj = String.class;
System.out.println(classObj);

Class<?> classObj2 = Class.forName("java.lang.String");
System.out.println(classObj2);

Class<?> classObj3 = "hello world".getClass();
System.out.println(classObj3);
```

**Output**:

```
class java.lang.String
class java.lang.String
class java.lang.String
```

Above are three examples of obtaining the `Class` object for the `String` class. Note the use of wildcards in the generic parameters. The reason for this is for some of these examples, the return type may be uncertain or not exactly of the type we are expecting.

### Obtaining Class Members

```java
// Step 1: Obtain Class Object
Class<?> classObj = Object.class;
System.out.println(classObj + "\n");

// Step 2: Get the declared methods
Member[] members = classObj.getDeclaredMethods();
for (Member member : members) {
   System.out.println(member);
}
```

In the above example, we obtain a `Class` object for the `Object` class, and then we get its methods and print them out to the console. Note that `getDeclaredMethods()` returns an array of objects of the type `Member`. This is the supertype for the `Field`, `Method`, and `Constructor` classes.

**Output**:

```
class java.lang.Object

protected void java.lang.Object.finalize() throws java.lang.Throwable
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public java.lang.String java.lang.Object.toString()
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()
```

### Using Class Members

```java
// Step 3: Using a class method
Method method = (Method) classObj.getDeclaredMethod("getClass");
System.out.println(method);
System.out.println(method.invoke("hello world"));
```

If we continue with the previous example, we can use `classObj` to get a specific method with the name `getClass` and invoke it.

**Output**:

```
public final native java.lang.Class java.lang.Object.getClass()
class java.lang.String
```

## Summary

- Reflection allows an executing Java program to examine or "introspect" upon itself and manipulate internal properties of the program.
- The API can be found in the `java.lang.reflect` package.
- The `java.lang.Class` class represents classes and interfaces in Java and it provides methods to query information about a class.
- Once you have a `Class` object, you can inspect its structure using methods such as `getFields()`, `getMethods()`, etc.
- Just as there is a class that represents `Class`, there are classes that represent constructors, fields, and methods. These classes contain methods for accessing metadata about or using these class members.