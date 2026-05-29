import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/**
 * Simple code examples for Functional Programming concepts
 * Read FunctionalProgramming_Theory.md first for detailed explanations
 */
public class FunctionalProgrammingExamples {
    
    public static void main(String[] args) {
        example1_ImperativeVsDeclarative();
        example2_PureVsImpure();
        example3_StatelessVsStateful();
        example4_SideEffects();
    }
    
    // ============================================
    // Example 1: Imperative vs Declarative
    // ============================================
    public static void example1_ImperativeVsDeclarative() {
        System.out.println("=== Example 1: Imperative vs Declarative ===\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // IMPERATIVE: HOW to do it (step-by-step)
        System.out.println("Imperative (HOW):");
        List<Integer> evenSquares1 = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            int num = numbers.get(i);
            if (num % 2 == 0) {
                evenSquares1.add(num * num);
            }
        }
        System.out.println("Result: " + evenSquares1);
        
        // DECLARATIVE: WHAT you want
        System.out.println("\nDeclarative (WHAT):");
        List<Integer> evenSquares2 = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Result: " + evenSquares2);
        System.out.println();
    }
    
    // ============================================
    // Example 2: Pure vs Impure Functions
    // ============================================
    
    // PURE FUNCTION - Always same output for same input
    public static int pureAdd(int a, int b) {
        return a + b;
    }
    
    // IMPURE FUNCTION - Depends on external state
    private static int counter = 0;
    public static int impureAdd(int a, int b) {
        counter++;  // Side effect!
        return a + b + counter;
    }
    
    public static void example2_PureVsImpure() {
        System.out.println("=== Example 2: Pure vs Impure Functions ===\n");
        
        // Pure function - predictable
        System.out.println("Pure Function:");
        System.out.println("pureAdd(5, 3) = " + pureAdd(5, 3));
        System.out.println("pureAdd(5, 3) = " + pureAdd(5, 3));
        System.out.println("pureAdd(5, 3) = " + pureAdd(5, 3));
        System.out.println("Always returns 8\n");
        
        // Impure function - unpredictable
        counter = 0;  // Reset
        System.out.println("Impure Function:");
        System.out.println("impureAdd(5, 3) = " + impureAdd(5, 3));
        System.out.println("impureAdd(5, 3) = " + impureAdd(5, 3));
        System.out.println("impureAdd(5, 3) = " + impureAdd(5, 3));
        System.out.println("Different results each time!\n");
    }
    
    // ============================================
    // Example 3: Stateless vs Stateful
    // ============================================
    
    // STATELESS - No internal state
    static class StatelessCalculator {
        public int add(int a, int b) {
            return a + b;
        }
    }
    
    // STATEFUL - Maintains internal state
    static class StatefulCalculator {
        private int total = 0;
        
        public int add(int value) {
            total += value;
            return total;
        }
    }
    
    public static void example3_StatelessVsStateful() {
        System.out.println("=== Example 3: Stateless vs Stateful ===\n");
        
        // Stateless
        System.out.println("Stateless:");
        StatelessCalculator stateless = new StatelessCalculator();
        System.out.println("add(5, 3) = " + stateless.add(5, 3));
        System.out.println("add(5, 3) = " + stateless.add(5, 3));
        System.out.println("add(5, 3) = " + stateless.add(5, 3));
        System.out.println("Always returns 8\n");
        
        // Stateful
        System.out.println("Stateful:");
        StatefulCalculator stateful = new StatefulCalculator();
        System.out.println("add(5) = " + stateful.add(5));
        System.out.println("add(3) = " + stateful.add(3));
        System.out.println("add(2) = " + stateful.add(2));
        System.out.println("Accumulates: 5, 8, 10\n");
    }
    
    // ============================================
    // Example 4: Side Effects
    // ============================================
    
    // NO SIDE EFFECTS
    public static int calculateDiscount(int price, double percent) {
        return (int) (price * (1 - percent / 100));
    }
    
    // HAS SIDE EFFECTS
    private static List<String> log = new ArrayList<>();
    public static int calculateDiscountWithLog(int price, double percent) {
        int result = (int) (price * (1 - percent / 100));
        log.add("Calculated: " + result);  // Side effect: modifies log
        System.out.println("Discount applied");  // Side effect: console output
        return result;
    }
    
    public static void example4_SideEffects() {
        System.out.println("=== Example 4: Side Effects ===\n");
        
        // Without side effects
        System.out.println("Without Side Effects:");
        int price1 = calculateDiscount(100, 10);
        int price2 = calculateDiscount(100, 10);
        System.out.println("Result 1: " + price1);
        System.out.println("Result 2: " + price2);
        System.out.println("No external changes\n");
        
        // With side effects
        System.out.println("With Side Effects:");
        log.clear();
        int price3 = calculateDiscountWithLog(100, 10);
        int price4 = calculateDiscountWithLog(100, 10);
        System.out.println("Result 1: " + price3);
        System.out.println("Result 2: " + price4);
        System.out.println("Log entries: " + log.size());
        System.out.println("External state changed!\n");
    }
}

// Made with Bob
