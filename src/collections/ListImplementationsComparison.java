package collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Comparison of Different List Implementations
 * Demonstrates when to use ArrayList, LinkedList, Vector, Stack, CopyOnWriteArrayList
 */
public class ListImplementationsComparison {
    
    public static void main(String[] args) {
        compareBasicOperations();
        comparePerformance();
        demonstrateUseCases();
    }
    
    // ============================================
    // Compare Basic Operations
    // ============================================
    public static void compareBasicOperations() {
        System.out.println("=== Comparing List Implementations ===\n");
        
        // 1. ArrayList
        System.out.println("1. ARRAYLIST:");
        List<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        System.out.println("   Type: Resizable array");
        System.out.println("   Data: " + arrayList);
        System.out.println("   Get by index: " + arrayList.get(1) + " (Fast - O(1))");
        System.out.println("   Thread-safe: No");
        System.out.println("   Best for: Random access, read-heavy operations");
        
        // 2. LinkedList
        System.out.println("\n2. LINKEDLIST:");
        List<String> linkedList = new LinkedList<>();
        linkedList.add("X");
        linkedList.add("Y");
        linkedList.add("Z");
        System.out.println("   Type: Doubly-linked list");
        System.out.println("   Data: " + linkedList);
        linkedList.add(0, "W");  // Fast at beginning
        System.out.println("   After add at start: " + linkedList);
        System.out.println("   Thread-safe: No");
        System.out.println("   Best for: Frequent insertions/deletions, Queue operations");
        
        // 3. Vector
        System.out.println("\n3. VECTOR (Legacy):");
        List<String> vector = new Vector<>();
        vector.add("P");
        vector.add("Q");
        vector.add("R");
        System.out.println("   Type: Synchronized resizable array");
        System.out.println("   Data: " + vector);
        System.out.println("   Thread-safe: Yes (synchronized)");
        System.out.println("   Best for: Legacy code only");
        System.out.println("   Note: Prefer ArrayList + synchronization");
        
        // 4. Stack
        System.out.println("\n4. STACK (Legacy):");
        Stack<String> stack = new Stack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        System.out.println("   Type: LIFO (Last-In-First-Out)");
        System.out.println("   Data: " + stack);
        System.out.println("   Pop: " + stack.pop() + " (removes 'Third')");
        System.out.println("   Peek: " + stack.peek() + " (views 'Second')");
        System.out.println("   Thread-safe: Yes (extends Vector)");
        System.out.println("   Best for: Legacy code, prefer Deque");
        
        // 5. CopyOnWriteArrayList
        System.out.println("\n5. COPYONWRITEARRAYLIST:");
        List<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("M");
        cowList.add("N");
        cowList.add("O");
        System.out.println("   Type: Thread-safe array (copy-on-write)");
        System.out.println("   Data: " + cowList);
        System.out.println("   Thread-safe: Yes (without explicit sync)");
        System.out.println("   Best for: Read-heavy concurrent operations");
        System.out.println("   Note: Expensive writes (creates copy)");
        
        System.out.println();
    }
    
    // ============================================
    // Compare Performance Characteristics
    // ============================================
    public static void comparePerformance() {
        System.out.println("=== Performance Comparison ===\n");
        
        System.out.println("┌─────────────────┬───────────┬────────────┬─────────┬───────────────────┐");
        System.out.println("│ Operation       │ ArrayList │ LinkedList │ Vector  │ CopyOnWriteArray  │");
        System.out.println("├─────────────────┼───────────┼────────────┼─────────┼───────────────────┤");
        System.out.println("│ get(index)      │ O(1) ⚡   │ O(n) 🐌    │ O(1) ⚡ │ O(1) ⚡           │");
        System.out.println("│ add(element)    │ O(1)* ⚡  │ O(1) ⚡    │ O(1)* ⚡│ O(n) 🐌           │");
        System.out.println("│ add(i, element) │ O(n) 🐌   │ O(n) 🐌    │ O(n) 🐌 │ O(n) 🐌           │");
        System.out.println("│ remove(index)   │ O(n) 🐌   │ O(n) 🐌    │ O(n) 🐌 │ O(n) 🐌           │");
        System.out.println("│ contains()      │ O(n) 🐌   │ O(n) 🐌    │ O(n) 🐌 │ O(n) 🐌           │");
        System.out.println("│ Thread-safe     │ ❌        │ ❌         │ ✅      │ ✅                │");
        System.out.println("│ Memory          │ Less      │ More       │ Less    │ Less              │");
        System.out.println("└─────────────────┴───────────┴────────────┴─────────┴───────────────────┘");
        System.out.println("* Amortized O(1), worst case O(n) when resizing\n");
        
        // Memory comparison
        System.out.println("Memory Usage:");
        System.out.println("ArrayList:   [E][E][E][Empty][Empty]  ← Contiguous, some waste");
        System.out.println("LinkedList:  [E|→] → [E|→] → [E|→]    ← More memory (pointers)");
        System.out.println("Vector:      [E][E][E][Empty][Empty]  ← Like ArrayList + sync overhead");
        System.out.println();
    }
    
    // ============================================
    // Demonstrate Use Cases
    // ============================================
    public static void demonstrateUseCases() {
        System.out.println("=== When to Use Which Implementation ===\n");
        
        // Use Case 1: Random Access (ArrayList)
        System.out.println("USE CASE 1: Random Access / Read-Heavy");
        System.out.println("Scenario: Accessing elements by index frequently");
        System.out.println("Best Choice: ArrayList");
        List<String> students = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David"));
        System.out.println("Students: " + students);
        System.out.println("Get student at index 2: " + students.get(2) + " (Fast!)");
        System.out.println();
        
        // Use Case 2: Frequent Insertions/Deletions (LinkedList)
        System.out.println("USE CASE 2: Frequent Insertions/Deletions");
        System.out.println("Scenario: Queue operations, adding/removing at ends");
        System.out.println("Best Choice: LinkedList");
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast("Task1");
        queue.addLast("Task2");
        queue.addLast("Task3");
        System.out.println("Queue: " + queue);
        System.out.println("Process: " + queue.removeFirst() + " (Fast at beginning!)");
        System.out.println("Remaining: " + queue);
        System.out.println();
        
        // Use Case 3: Thread-Safe Read-Heavy (CopyOnWriteArrayList)
        System.out.println("USE CASE 3: Thread-Safe Read-Heavy Operations");
        System.out.println("Scenario: Multiple threads reading, rare writes");
        System.out.println("Best Choice: CopyOnWriteArrayList");
        List<String> listeners = new CopyOnWriteArrayList<>();
        listeners.add("Listener1");
        listeners.add("Listener2");
        System.out.println("Event Listeners: " + listeners);
        System.out.println("Safe for concurrent reads without explicit synchronization");
        System.out.println();
        
        // Use Case 4: Thread-Safe Balanced Read-Write (Synchronized List)
        System.out.println("USE CASE 4: Thread-Safe Balanced Read-Write");
        System.out.println("Scenario: Multiple threads reading and writing");
        System.out.println("Best Choice: Collections.synchronizedList()");
        List<String> sharedList = Collections.synchronizedList(new ArrayList<>());
        sharedList.add("Item1");
        sharedList.add("Item2");
        System.out.println("Shared List: " + sharedList);
        System.out.println("Thread-safe for all operations");
        System.out.println();
        
        // Use Case 5: LIFO Operations (Stack - but prefer Deque)
        System.out.println("USE CASE 5: LIFO (Last-In-First-Out) Operations");
        System.out.println("Scenario: Undo operations, expression evaluation");
        System.out.println("Best Choice: Deque (ArrayDeque), not Stack");
        Deque<String> stack = new ArrayDeque<>();
        stack.push("Action1");
        stack.push("Action2");
        stack.push("Action3");
        System.out.println("Undo Stack: " + stack);
        System.out.println("Undo: " + stack.pop());
        System.out.println("Remaining: " + stack);
        System.out.println();
        
        // Decision Tree
        System.out.println("=== DECISION TREE ===");
        System.out.println();
        System.out.println("Need thread-safety?");
        System.out.println("  ├─ Yes → Read-heavy?");
        System.out.println("  │         ├─ Yes → CopyOnWriteArrayList");
        System.out.println("  │         └─ No  → Collections.synchronizedList(ArrayList)");
        System.out.println("  │");
        System.out.println("  └─ No  → Frequent insertions/deletions?");
        System.out.println("            ├─ Yes → LinkedList");
        System.out.println("            └─ No  → ArrayList (default choice)");
        System.out.println();
        
        // Summary
        System.out.println("=== QUICK SUMMARY ===");
        System.out.println();
        System.out.println("✅ ArrayList:");
        System.out.println("   • Default choice for most cases");
        System.out.println("   • Fast random access");
        System.out.println("   • Good for read-heavy operations");
        System.out.println();
        System.out.println("✅ LinkedList:");
        System.out.println("   • Frequent insertions/deletions");
        System.out.println("   • Queue/Deque operations");
        System.out.println("   • Don't need random access");
        System.out.println();
        System.out.println("✅ CopyOnWriteArrayList:");
        System.out.println("   • Concurrent read-heavy operations");
        System.out.println("   • Rare modifications");
        System.out.println("   • No ConcurrentModificationException");
        System.out.println();
        System.out.println("❌ Vector:");
        System.out.println("   • Legacy code only");
        System.out.println("   • Use ArrayList + synchronization instead");
        System.out.println();
        System.out.println("❌ Stack:");
        System.out.println("   • Legacy code only");
        System.out.println("   • Use ArrayDeque instead");
        System.out.println();
    }
}

// Made with Bob
