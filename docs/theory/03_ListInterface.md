# List Interface - Complete Guide

## Table of Contents
1. [List Interface Overview](#1-list-interface-overview)
2. [Ways to Create List](#2-ways-to-create-list)
3. [List Implementations](#3-list-implementations)
4. [ArrayList vs LinkedList vs Vector](#4-arraylist-vs-linkedlist-vs-vector)
5. [Common List Operations](#5-common-list-operations)
6. [Interview Questions](#6-interview-questions)

---

## 1. List Interface Overview

### **What is List?**
- **List** is an **interface** in Java Collections Framework
- Part of `java.util` package
- Extends `Collection` interface
- Represents an **ordered collection** (sequence)
- Allows **duplicate elements**
- Allows **null elements**
- Elements can be accessed by **index** (position)

### **Key Characteristics:**

| Feature | Description |
|---------|-------------|
| **Ordered** | Maintains insertion order |
| **Indexed** | Elements accessible by position (0-based) |
| **Duplicates** | Allows duplicate elements |
| **Null** | Allows null elements |
| **Dynamic** | Size can grow/shrink |

### **Hierarchy:**

```
        Iterable
            ↓
        Collection
            ↓
          List (Interface)
            ↓
    ┌───────┼───────┐
    ↓       ↓       ↓
ArrayList LinkedList Vector
                    ↓
                  Stack
```

---

## 2. Ways to Create List

### **Method 1: Using ArrayList Constructor**

```java
// Empty list
List<String> list1 = new ArrayList<>();

// With initial capacity
List<String> list2 = new ArrayList<>(20);

// From another collection
List<String> list3 = new ArrayList<>(existingList);
```

### **Method 2: Using Arrays.asList()**

```java
// Fixed-size list (cannot add/remove)
List<String> list = Arrays.asList("A", "B", "C");

// Can modify existing elements
list.set(0, "X");  // ✅ Works

// Cannot add/remove
list.add("D");     // ❌ UnsupportedOperationException
list.remove(0);    // ❌ UnsupportedOperationException
```

**⚠️ Important:** `Arrays.asList()` returns a **fixed-size** list backed by an array.

### **Method 3: Using List.of() (Java 9+)**

```java
// Immutable list
List<String> list = List.of("A", "B", "C");

// Cannot modify at all
list.set(0, "X");  // ❌ UnsupportedOperationException
list.add("D");     // ❌ UnsupportedOperationException
list.remove(0);    // ❌ UnsupportedOperationException
```

**⚠️ Important:** `List.of()` returns an **immutable** list.

### **Method 4: Using Collections.singletonList()**

```java
// Immutable list with single element
List<String> list = Collections.singletonList("A");

// Cannot modify
list.add("B");     // ❌ UnsupportedOperationException
```

### **Method 5: Using Collections.emptyList()**

```java
// Immutable empty list
List<String> list = Collections.emptyList();

// Cannot add elements
list.add("A");     // ❌ UnsupportedOperationException
```

### **Method 6: Using Collections.nCopies()**

```java
// List with n copies of element
List<String> list = Collections.nCopies(5, "A");
// Result: [A, A, A, A, A]
```

### **Method 7: Using Stream (Java 8+)**

```java
// From stream
List<Integer> list = Stream.of(1, 2, 3, 4, 5)
    .collect(Collectors.toList());

// From range
List<Integer> list2 = IntStream.range(1, 6)
    .boxed()
    .collect(Collectors.toList());

// From array
List<String> list3 = Arrays.stream(array)
    .collect(Collectors.toList());
```

### **Method 8: Using Collections.addAll()**

```java
List<String> list = new ArrayList<>();
Collections.addAll(list, "A", "B", "C");
```

### **Method 9: Double Brace Initialization (Not Recommended)**

```java
// Creates anonymous inner class
List<String> list = new ArrayList<String>() {{
    add("A");
    add("B");
    add("C");
}};
```

**⚠️ Warning:** Creates unnecessary anonymous class, avoid in production.

### **Method 10: Using LinkedList**

```java
List<String> list = new LinkedList<>();
list.add("A");
list.add("B");
```

### **Method 11: Using Vector (Legacy)**

```java
List<String> list = new Vector<>();
list.add("A");
list.add("B");
```

---

## 3. List Implementations

### **ArrayList**

**Description:** Resizable array implementation

**Characteristics:**
- ✅ Fast random access (O(1))
- ✅ Good for read-heavy operations
- ❌ Slow insertion/deletion in middle (O(n))
- ✅ Implements RandomAccess interface
- ❌ Not synchronized (not thread-safe)

**When to Use:**
- Frequent access by index
- More reads than writes
- Single-threaded environment

**Example:**
```java
List<String> list = new ArrayList<>();
list.add("Apple");
list.add("Banana");
String fruit = list.get(0);  // Fast O(1)
```

---

### **LinkedList**

**Description:** Doubly-linked list implementation

**Characteristics:**
- ❌ Slow random access (O(n))
- ✅ Fast insertion/deletion at ends (O(1))
- ✅ Fast insertion/deletion in middle (O(1) if position known)
- ✅ Implements Deque interface
- ❌ Not synchronized (not thread-safe)

**When to Use:**
- Frequent insertions/deletions
- Queue/Deque operations
- Don't need random access

**Example:**
```java
List<String> list = new LinkedList<>();
list.add("First");
list.add(0, "New First");  // Fast at beginning
```

---

### **Vector**

**Description:** Legacy synchronized resizable array

**Characteristics:**
- ✅ Synchronized (thread-safe)
- ❌ Slower than ArrayList (due to synchronization)
- ✅ Legacy class (from Java 1.0)
- ❌ Generally avoid in new code

**When to Use:**
- Legacy code compatibility
- Need thread-safety (but prefer CopyOnWriteArrayList)

**Example:**
```java
List<String> list = new Vector<>();
list.add("Item");
```

---

### **Stack**

**Description:** Extends Vector, LIFO (Last-In-First-Out)

**Characteristics:**
- ✅ Synchronized
- ✅ LIFO operations (push, pop, peek)
- ❌ Legacy class
- ❌ Prefer Deque implementations

**Example:**
```java
Stack<String> stack = new Stack<>();
stack.push("First");
stack.push("Second");
String top = stack.pop();  // "Second"
```

---

### **CopyOnWriteArrayList**

**Description:** Thread-safe variant of ArrayList

**Characteristics:**
- ✅ Thread-safe without explicit synchronization
- ✅ Good for read-heavy, write-rare scenarios
- ❌ Expensive writes (creates copy)
- ✅ Iterator never throws ConcurrentModificationException

**When to Use:**
- Concurrent read-heavy operations
- Rare modifications
- Need thread-safety

**Example:**
```java
List<String> list = new CopyOnWriteArrayList<>();
list.add("Item");
```

---

## 4. ArrayList vs LinkedList vs Vector

### **Performance Comparison:**

| Operation | ArrayList | LinkedList | Vector |
|-----------|-----------|------------|--------|
| **get(index)** | O(1) ⚡ | O(n) 🐌 | O(1) ⚡ |
| **add(element)** | O(1)* | O(1) ⚡ | O(1)* |
| **add(index, element)** | O(n) | O(n) | O(n) |
| **remove(index)** | O(n) | O(n) | O(n) |
| **contains(element)** | O(n) | O(n) | O(n) |
| **Thread-safe** | ❌ | ❌ | ✅ |
| **Memory** | Less | More | Less |

*Amortized O(1), worst case O(n) when resizing

### **Memory Usage:**

```
ArrayList:
[Element][Element][Element][Empty][Empty]
↑ Contiguous memory, some waste

LinkedList:
[Element|Next] → [Element|Next] → [Element|Next]
↑ More memory per element (pointers)

Vector:
[Element][Element][Element][Empty][Empty]
↑ Similar to ArrayList + synchronization overhead
```

### **When to Use Which:**

```
Use ArrayList when:
✅ Need fast random access
✅ More reads than writes
✅ Single-threaded
✅ Memory efficient

Use LinkedList when:
✅ Frequent insertions/deletions
✅ Queue/Deque operations
✅ Don't need random access
✅ OK with more memory

Use Vector when:
✅ Legacy code
✅ Need thread-safety (but prefer alternatives)

Avoid Vector:
❌ Use ArrayList + Collections.synchronizedList()
❌ Or use CopyOnWriteArrayList
```

---

## 5. Common List Operations

### **Adding Elements:**

```java
List<String> list = new ArrayList<>();

// Add at end
list.add("A");                    // [A]

// Add at specific index
list.add(0, "B");                 // [B, A]

// Add all from collection
list.addAll(Arrays.asList("C", "D"));  // [B, A, C, D]

// Add all at index
list.addAll(1, Arrays.asList("X", "Y"));  // [B, X, Y, A, C, D]
```

### **Accessing Elements:**

```java
// Get by index
String first = list.get(0);

// Get first/last (Java 21+)
String first = list.getFirst();
String last = list.getLast();

// Check if empty
boolean empty = list.isEmpty();

// Get size
int size = list.size();

// Check contains
boolean has = list.contains("A");

// Get index
int index = list.indexOf("A");      // First occurrence
int lastIndex = list.lastIndexOf("A");  // Last occurrence
```

### **Modifying Elements:**

```java
// Replace element
list.set(0, "Z");

// Remove by index
list.remove(0);

// Remove by object
list.remove("A");

// Remove all matching
list.removeAll(Arrays.asList("A", "B"));

// Retain only matching
list.retainAll(Arrays.asList("A", "B"));

// Clear all
list.clear();
```

### **Iterating:**

```java
// For-each loop
for (String item : list) {
    System.out.println(item);
}

// Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// ListIterator (bidirectional)
ListIterator<String> lit = list.listIterator();
while (lit.hasNext()) {
    System.out.println(lit.next());
}

// Java 8 forEach
list.forEach(System.out::println);

// Stream
list.stream().forEach(System.out::println);
```

### **Sorting:**

```java
// Natural order
Collections.sort(list);

// Reverse order
Collections.sort(list, Collections.reverseOrder());

// Custom comparator
Collections.sort(list, (a, b) -> a.length() - b.length());

// Java 8 List.sort()
list.sort(Comparator.naturalOrder());
list.sort(Comparator.reverseOrder());
list.sort((a, b) -> a.length() - b.length());
```

### **Searching:**

```java
// Binary search (list must be sorted)
int index = Collections.binarySearch(list, "A");

// Find max/min
String max = Collections.max(list);
String min = Collections.min(list);
```

### **Converting:**

```java
// List to Array
String[] array = list.toArray(new String[0]);

// List to Set
Set<String> set = new HashSet<>(list);

// List to Stream
Stream<String> stream = list.stream();

// Sublist
List<String> sublist = list.subList(0, 3);  // [0, 3)
```

---

## 6. Interview Questions

### **Q1: What is the difference between List and Set?**

**Answer:**
> "List is an ordered collection that allows duplicates and provides index-based access. Set is an unordered collection that doesn't allow duplicates. List maintains insertion order and allows accessing elements by position, while Set focuses on uniqueness. For example, List can have [1, 2, 2, 3] but Set would only have [1, 2, 3]."

**Key Differences:**

| Feature | List | Set |
|---------|------|-----|
| Order | Maintains order | No guaranteed order* |
| Duplicates | Allowed | Not allowed |
| Index access | Yes | No |
| Null | Multiple nulls | One null (HashSet) |

*LinkedHashSet maintains insertion order

---

### **Q2: What is the difference between ArrayList and LinkedList?**

**Answer:**
> "ArrayList uses a dynamic array internally, providing fast random access (O(1)) but slower insertions/deletions in the middle (O(n)). LinkedList uses a doubly-linked list, providing fast insertions/deletions (O(1) at ends) but slower random access (O(n)). Choose ArrayList for read-heavy operations and LinkedList for write-heavy operations with frequent insertions/deletions."

---

### **Q3: What does Arrays.asList() return?**

**Answer:**
> "Arrays.asList() returns a fixed-size list backed by the original array. You can modify existing elements using set(), but you cannot add or remove elements. Any changes to the list are reflected in the original array and vice versa. If you need a mutable list, wrap it in a new ArrayList: `new ArrayList<>(Arrays.asList(...))`."

**Example:**
```java
String[] array = {"A", "B", "C"};
List<String> list = Arrays.asList(array);
list.set(0, "X");     // ✅ Works, array[0] becomes "X"
list.add("D");        // ❌ UnsupportedOperationException
```

---

### **Q4: What is the difference between List.of() and Arrays.asList()?**

**Answer:**
> "List.of() (Java 9+) returns an immutable list - you cannot modify, add, or remove elements. Arrays.asList() returns a fixed-size list - you can modify existing elements but cannot add or remove. List.of() also doesn't allow null elements, while Arrays.asList() does."

**Comparison:**

| Feature | List.of() | Arrays.asList() |
|---------|-----------|-----------------|
| Modify elements | ❌ | ✅ |
| Add/Remove | ❌ | ❌ |
| Null elements | ❌ | ✅ |
| Java version | 9+ | 1.2+ |

---

### **Q5: When should you use Vector instead of ArrayList?**

**Answer:**
> "In modern Java, you should rarely use Vector. It's a legacy class that's synchronized, making it thread-safe but slower than ArrayList. If you need thread-safety, prefer CopyOnWriteArrayList for read-heavy scenarios or use Collections.synchronizedList(new ArrayList<>()) for general cases. Vector is only useful for maintaining compatibility with legacy code."

---

### **Q6: How do you make an ArrayList thread-safe?**

**Answer:**
> "There are three main ways:
> 1. Use Collections.synchronizedList(): `List<String> list = Collections.synchronizedList(new ArrayList<>());`
> 2. Use CopyOnWriteArrayList: `List<String> list = new CopyOnWriteArrayList<>();` (best for read-heavy)
> 3. Use external synchronization with synchronized blocks
> 
> CopyOnWriteArrayList is preferred for concurrent read-heavy operations, while synchronizedList is better for balanced read-write scenarios."

---

### **Q7: What is the time complexity of ArrayList operations?**

**Answer:**
> "ArrayList operations have the following time complexities:
> - get(index): O(1) - direct array access
> - add(element): O(1) amortized - may need to resize array
> - add(index, element): O(n) - need to shift elements
> - remove(index): O(n) - need to shift elements
> - contains(element): O(n) - linear search
> - size(): O(1) - stored as field"

---

## Quick Reference Summary

### **Creating Lists:**

```java
// Mutable
new ArrayList<>()                    // Empty, mutable
new ArrayList<>(collection)          // From collection
new LinkedList<>()                   // Linked list

// Fixed-size
Arrays.asList("A", "B")             // Can modify elements

// Immutable
List.of("A", "B")                   // Java 9+
Collections.singletonList("A")      // Single element
Collections.emptyList()             // Empty

// Thread-safe
new CopyOnWriteArrayList<>()        // Concurrent
Collections.synchronizedList(list)  // Synchronized wrapper
```

### **Common Patterns:**

```java
// Convert Arrays.asList to mutable
List<String> list = new ArrayList<>(Arrays.asList("A", "B"));

// Create and populate
List<String> list = new ArrayList<>();
Collections.addAll(list, "A", "B", "C");

// From stream
List<Integer> list = Stream.of(1, 2, 3)
    .collect(Collectors.toList());
```

---

**Master the List interface and you'll handle most collection scenarios! 📚**