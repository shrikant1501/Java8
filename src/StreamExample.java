import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        
        // Sample data
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        System.out.println("=== IMPLICIT WAY (Real Project Style) ===\n");
        
        // Example 1: Filter even numbers and square them
        System.out.println("1. Even numbers squared:");
        numbers.stream()
            .filter(n -> n % 2 == 0)           // Predicate (implicit)
            .map(n -> n * n)                    // Function (implicit)
            .forEach(System.out::println);      // Consumer (implicit)
        
        // Example 2: Filter and collect to new list
        System.out.println("\n2. Numbers greater than 5:");
        List<Integer> filtered = numbers.stream()
            .filter(n -> n > 5)                 // Predicate (implicit)
            .collect(Collectors.toList());
        System.out.println(filtered);
        
        // Example 3: Transform strings to uppercase
        System.out.println("\n3. Names in uppercase:");
        names.stream()
            .map(name -> name.toUpperCase())    // Function (implicit)
            .forEach(System.out::println);      // Consumer (implicit)
        
        // Example 4: Filter by length and count
        System.out.println("\n4. Names with length > 3:");
        long count = names.stream()
            .filter(name -> name.length() > 3)  // Predicate (implicit)
            .count();
        System.out.println("Count: " + count);
        
        // Example 5: Chain multiple operations
        System.out.println("\n5. Complex pipeline:");
        List<String> result = names.stream()
            .filter(name -> name.length() > 3)  // Predicate
            .map(name -> name.toUpperCase())    // Function
            .map(name -> "Hello, " + name)      // Function
            .collect(Collectors.toList());
        result.forEach(System.out::println);    // Consumer
        
        // Example 6: Sum of numbers
        System.out.println("\n6. Sum of all numbers:");
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);        // BinaryOperator (implicit)
        System.out.println("Sum: " + sum);
        
        // Example 7: Find first match
        System.out.println("\n7. First even number:");
        numbers.stream()
            .filter(n -> n % 2 == 0)            // Predicate
            .findFirst()
            .ifPresent(System.out::println);    // Consumer
        
        // Example 8: Check if any/all match
        System.out.println("\n8. Any number > 5?");
        boolean anyMatch = numbers.stream()
            .anyMatch(n -> n > 5);              // Predicate (implicit)
        System.out.println(anyMatch);
        
        System.out.println("\n9. All numbers positive?");
        boolean allMatch = numbers.stream()
            .allMatch(n -> n > 0);              // Predicate (implicit)
        System.out.println(allMatch);
    }
}

// Made with Bob
