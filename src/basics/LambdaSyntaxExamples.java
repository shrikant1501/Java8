import java.util.*;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lambda Syntax Examples
 * Read Lambda_Syntax_Theory.md for detailed explanations
 */
public class LambdaSyntaxExamples {
    
    public static void main(String[] args) {
        example1_NoParameter();
        example2_SingleParameter();
        example3_MultipleParameters();
        example4_BlockLambda();
        example5_VariableCapture();
        example6_EffectivelyFinal();
    }
    
    // ============================================
    // Example 1: No Parameter Lambda
    // ============================================
    public static void example1_NoParameter() {
        System.out.println("=== Example 1: No Parameter Lambda ===\n");
        
        // Runnable - no parameters, no return
        Runnable task = () -> System.out.println("Task is running");
        task.run();
        
        // Supplier - no parameters, returns value
        Supplier<String> greeting = () -> "Hello World";
        System.out.println(greeting.get());
        
        Supplier<Double> random = () -> Math.random();
        System.out.println("Random: " + random.get());
        
        // Custom interface
        Greeter greeter = () -> "Welcome!";
        System.out.println(greeter.greet());
        
        System.out.println();
    }
    
    @FunctionalInterface
    interface Greeter {
        String greet();
    }
    
    // ============================================
    // Example 2: Single Parameter Lambda
    // ============================================
    public static void example2_SingleParameter() {
        System.out.println("=== Example 2: Single Parameter Lambda ===\n");
        
        // Without parentheses (preferred for single parameter)
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        
        // With parentheses (also valid)
        Predicate<Integer> isPositive = (n) -> n > 0;
        System.out.println("Is 5 positive? " + isPositive.test(5));
        
        // Function - transforms input
        Function<String, Integer> length = s -> s.length();
        System.out.println("Length of 'Hello': " + length.apply("Hello"));
        
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("Square of 5: " + square.apply(5));
        
        // Consumer - performs action
        Consumer<String> printer = msg -> System.out.println("Message: " + msg);
        printer.accept("Lambda is cool!");
        
        // With explicit type (rarely needed)
        Function<String, Integer> length2 = (String s) -> s.length();
        System.out.println("Length with type: " + length2.apply("Java"));
        
        System.out.println();
    }
    
    // ============================================
    // Example 3: Multiple Parameters Lambda
    // ============================================
    public static void example3_MultipleParameters() {
        System.out.println("=== Example 3: Multiple Parameters Lambda ===\n");
        
        // BiFunction - two parameters, returns result
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.apply(5, 3));
        
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println(concat.apply("Hello", "World"));
        
        // BiPredicate - two parameters, returns boolean
        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
        System.out.println("Is 5 > 3? " + isGreater.test(5, 3));
        
        // Comparator - two parameters for comparison
        Comparator<String> byLength = (s1, s2) -> s1.length() - s2.length();
        List<String> names = new ArrayList<>(Arrays.asList("John", "Alice", "Bob"));
        names.sort(byLength);
        System.out.println("Sorted by length: " + names);
        
        // Custom interface with 3 parameters
        Calculator sum = (a, b, c) -> a + b + c;
        System.out.println("Sum of 1, 2, 3: " + sum.calculate(1, 2, 3));
        
        // With explicit types
        BiFunction<Integer, Integer, Integer> multiply = (Integer x, Integer y) -> x * y;
        System.out.println("3 * 4 = " + multiply.apply(3, 4));
        
        System.out.println();
    }
    
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b, int c);
    }
    
    // ============================================
    // Example 4: Block Lambda
    // ============================================
    public static void example4_BlockLambda() {
        System.out.println("=== Example 4: Block Lambda ===\n");
        
        // Simple block (single statement)
        Function<Integer, Integer> square = x -> {
            return x * x;
        };
        System.out.println("Square of 5: " + square.apply(5));
        
        // Multiple statements
        Function<Integer, String> describe = n -> {
            String result;
            if (n > 0) {
                result = "Positive";
            } else if (n < 0) {
                result = "Negative";
            } else {
                result = "Zero";
            }
            return result;
        };
        System.out.println("5 is: " + describe.apply(5));
        System.out.println("-3 is: " + describe.apply(-3));
        System.out.println("0 is: " + describe.apply(0));
        
        // With local variables
        BiFunction<Integer, Integer, Integer> calculate = (a, b) -> {
            int sum = a + b;
            int product = a * b;
            int result = sum + product;
            return result;
        };
        System.out.println("Calculate(3, 4): " + calculate.apply(3, 4));
        
        // With loop
        Consumer<Integer> printTable = n -> {
            System.out.println("Multiplication table of " + n + ":");
            for (int i = 1; i <= 5; i++) {
                System.out.println(n + " x " + i + " = " + (n * i));
            }
        };
        printTable.accept(3);
        
        // Void return type (no return needed)
        Consumer<String> logger = msg -> {
            String prefix = "[LOG]";
            System.out.println(prefix + " " + msg);
        };
        logger.accept("Application started");
        
        System.out.println();
    }
    
    // ============================================
    // Example 5: Variable Capture
    // ============================================
    public static void example5_VariableCapture() {
        System.out.println("=== Example 5: Variable Capture ===\n");
        
        // Capturing local variable
        int multiplier = 10;
        Function<Integer, Integer> multiply = x -> x * multiplier;
        System.out.println("5 * 10 = " + multiply.apply(5));
        
        // Capturing method parameter
        Consumer<String> greeter = createGreeter("Hello");
        greeter.accept("John");
        greeter.accept("Alice");
        
        // Capturing instance variable
        Calculator2 calc = new Calculator2();
        Function<Integer, Integer> adder = calc.getAdder();
        System.out.println("50 + base(100) = " + adder.apply(50));
        
        // Capturing static variable
        Predicate<Integer> validator = Config.getValidator();
        System.out.println("Is 500 valid? " + validator.test(500));
        System.out.println("Is 1500 valid? " + validator.test(1500));
        
        System.out.println();
    }
    
    // Helper method that returns lambda capturing parameter
    public static Consumer<String> createGreeter(String prefix) {
        return name -> System.out.println(prefix + " " + name);
    }
    
    // Class with instance variable
    static class Calculator2 {
        private int base = 100;
        
        public Function<Integer, Integer> getAdder() {
            return x -> x + base;
        }
    }
    
    // Class with static variable
    static class Config {
        static int MAX_VALUE = 1000;
        
        public static Predicate<Integer> getValidator() {
            return x -> x <= MAX_VALUE;
        }
    }
    
    // ============================================
    // Example 6: Effectively Final Variables
    // ============================================
    public static void example6_EffectivelyFinal() {
        System.out.println("=== Example 6: Effectively Final Variables ===\n");
        
        // Valid: Effectively final (never modified)
        int x = 10;
        Runnable r1 = () -> System.out.println("x = " + x);
        r1.run();
        
        // Valid: Explicitly final
        final int y = 20;
        Runnable r2 = () -> System.out.println("y = " + y);
        r2.run();
        
        // Invalid example (commented out - won't compile)
        // int z = 30;
        // z = 40;  // Modified!
        // Runnable r3 = () -> System.out.println(z);  // ERROR!
        
        // Workaround 1: Use array
        System.out.println("\nWorkaround 1: Array");
        int[] counter = {0};
        Runnable increment1 = () -> {
            counter[0]++;
            System.out.println("Counter: " + counter[0]);
        };
        increment1.run();
        increment1.run();
        increment1.run();
        
        // Workaround 2: Use AtomicInteger
        System.out.println("\nWorkaround 2: AtomicInteger");
        AtomicInteger atomicCounter = new AtomicInteger(0);
        Runnable increment2 = () -> {
            System.out.println("Atomic Counter: " + atomicCounter.incrementAndGet());
        };
        increment2.run();
        increment2.run();
        increment2.run();
        
        // Workaround 3: Use instance variable
        System.out.println("\nWorkaround 3: Instance Variable");
        Counter counterObj = new Counter();
        Runnable increment3 = counterObj.getIncrementer();
        increment3.run();
        increment3.run();
        increment3.run();
        
        System.out.println();
    }
    
    // Helper class for instance variable workaround
    static class Counter {
        private int count = 0;
        
        public Runnable getIncrementer() {
            return () -> {
                count++;
                System.out.println("Instance Counter: " + count);
            };
        }
    }
    
    // ============================================
    // Bonus: Comparison Examples
    // ============================================
    public static void bonusComparison() {
        System.out.println("=== Bonus: Before vs After Java 8 ===\n");
        
        // Before Java 8 (Anonymous Class)
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println("Old way");
            }
        };
        
        // After Java 8 (Lambda)
        Runnable newWay = () -> System.out.println("New way");
        
        oldWay.run();
        newWay.run();
        
        // Before Java 8 (Comparator)
        Comparator<String> oldComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };
        
        // After Java 8 (Lambda)
        Comparator<String> newComparator = (s1, s2) -> s1.length() - s2.length();
        
        System.out.println("Compare 'Hi' and 'Hello': " + newComparator.compare("Hi", "Hello"));
    }
}

// Made with Bob
