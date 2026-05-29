package collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.*;

/**
 * List Interface - Complete Examples
 * Read docs/theory/03_ListInterface.md for detailed explanations
 */
public class ListExamples {
    
    public static void main(String[] args) {
        example1_CreatingLists();
        example2_ArrayListVsLinkedList();
        example3_CommonOperations();
        example4_IteratingLists();
        example5_SortingAndSearching();
        example6_ThreadSafeLists();
        example7_ImmutableLists();
    }
    
    // ============================================
    // Example 1: Different Ways to Create Lists
    // ============================================
    public static void example1_CreatingLists() {
        System.out.println("=== Example 1: Creating Lists ===\n");
        
        // Method 1: ArrayList constructor
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        System.out.println("1. ArrayList: " + list1);
        
        // Method 2: ArrayList with initial capacity
        List<String> list2 = new ArrayList<>(20);
        list2.add("X");
        System.out.println("2. ArrayList (capacity 20): " + list2);
        
        // Method 3: ArrayList from collection
        List<String> list3 = new ArrayList<>(list1);
        System.out.println("3. ArrayList from collection: " + list3);
        
        // Method 4: Arrays.asList() - Fixed size
        List<String> list4 = Arrays.asList("C", "D", "E");
        System.out.println("4. Arrays.asList(): " + list4);
        // list4.add("F");  // ❌ UnsupportedOperationException
        list4.set(0, "Z");  // ✅ Can modify
        System.out.println("   After set(0, 'Z'): " + list4);
        
        // Method 5: List.of() - Immutable (Java 9+)
        List<String> list5 = List.of("F", "G", "H");
        System.out.println("5. List.of(): " + list5);
        // list5.set(0, "X");  // ❌ UnsupportedOperationException
        
        // Method 6: Collections.singletonList() - Immutable
        List<String> list6 = Collections.singletonList("Single");
        System.out.println("6. singletonList(): " + list6);
        
        // Method 7: Collections.emptyList() - Immutable
        List<String> list7 = Collections.emptyList();
        System.out.println("7. emptyList(): " + list7);
        
        // Method 8: Collections.nCopies()
        List<String> list8 = Collections.nCopies(5, "Copy");
        System.out.println("8. nCopies(5, 'Copy'): " + list8);
        
        // Method 9: Stream to List
        List<Integer> list9 = Stream.of(1, 2, 3, 4, 5)
            .collect(Collectors.toList());
        System.out.println("9. Stream to List: " + list9);
        
        // Method 10: IntStream to List
        List<Integer> list10 = IntStream.range(1, 6)
            .boxed()
            .collect(Collectors.toList());
        System.out.println("10. IntStream to List: " + list10);
        
        // Method 11: Collections.addAll()
        List<String> list11 = new ArrayList<>();
        Collections.addAll(list11, "I", "J", "K");
        System.out.println("11. Collections.addAll(): " + list11);
        
        // Method 12: LinkedList
        List<String> list12 = new LinkedList<>();
        list12.add("L");
        list12.add("M");
        System.out.println("12. LinkedList: " + list12);
        
        // Method 13: Vector (Legacy)
        List<String> list13 = new Vector<>();
        list13.add("N");
        list13.add("O");
        System.out.println("13. Vector: " + list13);
        
        // Convert Arrays.asList to mutable ArrayList
        List<String> mutableList = new ArrayList<>(Arrays.asList("P", "Q", "R"));
        mutableList.add("S");  // ✅ Works
        System.out.println("14. Mutable from Arrays.asList: " + mutableList);
        
        System.out.println();
    }
    
    // ============================================
    // Example 2: ArrayList vs LinkedList
    // ============================================
    public static void example2_ArrayListVsLinkedList() {
        System.out.println("=== Example 2: ArrayList vs LinkedList ===\n");
        
        // ArrayList - Fast random access
        List<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        
        System.out.println("ArrayList:");
        System.out.println("  Get by index (fast): " + arrayList.get(1));
        System.out.println("  Size: " + arrayList.size());
        
        // LinkedList - Fast insertion/deletion
        List<String> linkedList = new LinkedList<>();
        linkedList.add("X");
        linkedList.add("Y");
        linkedList.add("Z");
        
        System.out.println("\nLinkedList:");
        System.out.println("  Get by index (slower): " + linkedList.get(1));
        linkedList.add(0, "W");  // Fast at beginning
        System.out.println("  After add at beginning: " + linkedList);
        
        // Performance comparison
        System.out.println("\nPerformance Characteristics:");
        System.out.println("ArrayList:");
        System.out.println("  ✅ Fast: get(index), iteration");
        System.out.println("  ❌ Slow: add/remove in middle");
        System.out.println("\nLinkedList:");
        System.out.println("  ✅ Fast: add/remove at ends");
        System.out.println("  ❌ Slow: get(index), random access");
        
        System.out.println();
    }
    
    // ============================================
    // Example 3: Common List Operations
    // ============================================
    public static void example3_CommonOperations() {
        System.out.println("=== Example 3: Common Operations ===\n");
        
        List<String> list = new ArrayList<>();
        
        // Adding elements
        System.out.println("Adding elements:");
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("  After add: " + list);
        
        list.add(1, "Avocado");  // Add at index
        System.out.println("  After add(1, 'Avocado'): " + list);
        
        list.addAll(Arrays.asList("Date", "Elderberry"));
        System.out.println("  After addAll: " + list);
        
        // Accessing elements
        System.out.println("\nAccessing elements:");
        System.out.println("  get(0): " + list.get(0));
        System.out.println("  size(): " + list.size());
        System.out.println("  isEmpty(): " + list.isEmpty());
        System.out.println("  contains('Banana'): " + list.contains("Banana"));
        System.out.println("  indexOf('Cherry'): " + list.indexOf("Cherry"));
        
        // Modifying elements
        System.out.println("\nModifying elements:");
        list.set(0, "Apricot");
        System.out.println("  After set(0, 'Apricot'): " + list);
        
        // Removing elements
        System.out.println("\nRemoving elements:");
        list.remove(0);  // Remove by index
        System.out.println("  After remove(0): " + list);
        
        list.remove("Banana");  // Remove by object
        System.out.println("  After remove('Banana'): " + list);
        
        // Sublist
        System.out.println("\nSublist:");
        List<String> sublist = list.subList(0, 2);
        System.out.println("  subList(0, 2): " + sublist);
        
        // Convert to array
        System.out.println("\nConvert to array:");
        String[] array = list.toArray(new String[0]);
        System.out.println("  Array: " + Arrays.toString(array));
        
        System.out.println();
    }
    
    // ============================================
    // Example 4: Iterating Lists
    // ============================================
    public static void example4_IteratingLists() {
        System.out.println("=== Example 4: Iterating Lists ===\n");
        
        List<String> list = Arrays.asList("A", "B", "C", "D");
        
        // Method 1: For-each loop
        System.out.println("1. For-each loop:");
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
        
        // Method 2: Traditional for loop
        System.out.println("\n2. Traditional for loop:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        
        // Method 3: Iterator
        System.out.println("\n3. Iterator:");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // Method 4: ListIterator (bidirectional)
        System.out.println("\n4. ListIterator (forward):");
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();
        
        System.out.println("\n5. ListIterator (backward):");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();
        
        // Method 5: forEach (Java 8)
        System.out.println("\n6. forEach method:");
        list.forEach(item -> System.out.print(item + " "));
        System.out.println();
        
        // Method 6: forEach with method reference
        System.out.println("\n7. forEach with method reference:");
        list.forEach(System.out::print);
        System.out.println();
        
        // Method 7: Stream
        System.out.println("\n8. Stream:");
        list.stream().forEach(item -> System.out.print(item + " "));
        System.out.println("\n");
    }
    
    // ============================================
    // Example 5: Sorting and Searching
    // ============================================
    public static void example5_SortingAndSearching() {
        System.out.println("=== Example 5: Sorting and Searching ===\n");
        
        List<String> fruits = new ArrayList<>(Arrays.asList("Banana", "Apple", "Cherry", "Date"));
        
        // Sorting
        System.out.println("Original: " + fruits);
        
        // Natural order
        Collections.sort(fruits);
        System.out.println("Sorted (natural): " + fruits);
        
        // Reverse order
        Collections.sort(fruits, Collections.reverseOrder());
        System.out.println("Sorted (reverse): " + fruits);
        
        // Custom comparator (by length)
        Collections.sort(fruits, (a, b) -> a.length() - b.length());
        System.out.println("Sorted (by length): " + fruits);
        
        // Java 8 List.sort()
        fruits.sort(Comparator.naturalOrder());
        System.out.println("List.sort() natural: " + fruits);
        
        fruits.sort(Comparator.reverseOrder());
        System.out.println("List.sort() reverse: " + fruits);
        
        // Searching
        System.out.println("\nSearching:");
        fruits.sort(Comparator.naturalOrder());  // Must be sorted for binary search
        int index = Collections.binarySearch(fruits, "Cherry");
        System.out.println("Binary search 'Cherry': index " + index);
        
        // Max and Min
        String max = Collections.max(fruits);
        String min = Collections.min(fruits);
        System.out.println("Max: " + max + ", Min: " + min);
        
        // Numbers example
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.println("\nNumbers: " + numbers);
        
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        System.out.println("Max: " + Collections.max(numbers));
        System.out.println("Min: " + Collections.min(numbers));
        
        System.out.println();
    }
    
    // ============================================
    // Example 6: Thread-Safe Lists
    // ============================================
    public static void example6_ThreadSafeLists() {
        System.out.println("=== Example 6: Thread-Safe Lists ===\n");
        
        // Method 1: Collections.synchronizedList()
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("A");
        syncList.add("B");
        System.out.println("1. Synchronized List: " + syncList);
        
        // Method 2: CopyOnWriteArrayList
        List<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("X");
        cowList.add("Y");
        System.out.println("2. CopyOnWriteArrayList: " + cowList);
        
        // Method 3: Vector (Legacy)
        List<String> vector = new Vector<>();
        vector.add("P");
        vector.add("Q");
        System.out.println("3. Vector (legacy): " + vector);
        
        System.out.println("\nThread-Safety Comparison:");
        System.out.println("Collections.synchronizedList():");
        System.out.println("  ✅ Thread-safe");
        System.out.println("  ✅ Good for balanced read-write");
        System.out.println("  ⚠️  Must synchronize on list for iteration");
        
        System.out.println("\nCopyOnWriteArrayList:");
        System.out.println("  ✅ Thread-safe");
        System.out.println("  ✅ Best for read-heavy operations");
        System.out.println("  ❌ Expensive writes (creates copy)");
        System.out.println("  ✅ Iterator never throws ConcurrentModificationException");
        
        System.out.println("\nVector:");
        System.out.println("  ✅ Thread-safe");
        System.out.println("  ❌ Legacy, slower than alternatives");
        System.out.println("  ❌ Avoid in new code");
        
        System.out.println();
    }
    
    // ============================================
    // Example 7: Immutable Lists
    // ============================================
    public static void example7_ImmutableLists() {
        System.out.println("=== Example 7: Immutable Lists ===\n");
        
        // Method 1: List.of() (Java 9+)
        List<String> immutable1 = List.of("A", "B", "C");
        System.out.println("1. List.of(): " + immutable1);
        
        // Method 2: Collections.singletonList()
        List<String> immutable2 = Collections.singletonList("Single");
        System.out.println("2. singletonList(): " + immutable2);
        
        // Method 3: Collections.emptyList()
        List<String> immutable3 = Collections.emptyList();
        System.out.println("3. emptyList(): " + immutable3);
        
        // Method 4: Collections.unmodifiableList()
        List<String> mutable = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        List<String> immutable4 = Collections.unmodifiableList(mutable);
        System.out.println("4. unmodifiableList(): " + immutable4);
        
        System.out.println("\nTrying to modify immutable lists:");
        try {
            immutable1.add("D");
        } catch (UnsupportedOperationException e) {
            System.out.println("  List.of() - Cannot add: " + e.getClass().getSimpleName());
        }
        
        try {
            immutable1.set(0, "X");
        } catch (UnsupportedOperationException e) {
            System.out.println("  List.of() - Cannot set: " + e.getClass().getSimpleName());
        }
        
        // Arrays.asList() - Fixed size but can modify
        List<String> fixedSize = Arrays.asList("P", "Q", "R");
        System.out.println("\nArrays.asList() - Fixed size:");
        System.out.println("  Original: " + fixedSize);
        
        fixedSize.set(0, "Z");  // ✅ Can modify
        System.out.println("  After set(0, 'Z'): " + fixedSize);
        
        try {
            fixedSize.add("S");  // ❌ Cannot add
        } catch (UnsupportedOperationException e) {
            System.out.println("  Cannot add: " + e.getClass().getSimpleName());
        }
        
        System.out.println("\nComparison:");
        System.out.println("List.of():");
        System.out.println("  ❌ Cannot modify, add, or remove");
        System.out.println("  ❌ No null elements");
        
        System.out.println("\nArrays.asList():");
        System.out.println("  ✅ Can modify existing elements");
        System.out.println("  ❌ Cannot add or remove");
        System.out.println("  ✅ Allows null elements");
        
        System.out.println();
    }
}

// Made with Bob
