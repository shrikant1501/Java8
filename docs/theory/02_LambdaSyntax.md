# Lambda Expressions - Complete Syntax Guide

## Table of Contents
1. [Basic Lambda Syntax](#1-basic-lambda-syntax)
2. [No Parameter Lambda](#2-no-parameter-lambda)
3. [Single Parameter Lambda](#3-single-parameter-lambda)
4. [Multiple Parameter Lambda](#4-multiple-parameter-lambda)
5. [Block Lambda](#5-block-lambda)
6. [Variable Capture](#6-variable-capture)
7. [Effectively Final Variables](#7-effectively-final-variables)
8. [Interview Questions](#8-interview-questions)

---

## 1. Basic Lambda Syntax

### **General Syntax:**
```
(parameters) -> expression
```
or
```
(parameters) -> { statements; }
```

### **Components:**

| Component | Description | Example |
|-----------|-------------|---------|
| `()` | Parameter list | `(x, y)` |
| `->` | Arrow operator | `->` |
| Expression/Block | Body | `x + y` or `{ return x + y; }` |

### **Lambda vs Anonymous Class:**

**Before Java 8 (Anonymous Class):**
```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};
```

**After Java 8 (Lambda):**
```java
Runnable r = () -> System.out.println("Hello");
```

**Reduction:** 5 lines → 1 line!

---

## 2. No Parameter Lambda

### **Syntax:**
```java
() -> expression
```

### **When to Use:**
- When functional interface method takes no parameters
- Examples: `Runnable`, `Supplier<T>`

### **Examples:**

#### **With Runnable:**
```java
// Lambda with no parameters
Runnable task = () -> System.out.println("Task running");
task.run();
```

#### **With Supplier:**
```java
// Returns a value, takes no input
Supplier<String> greeting = () -> "Hello World";
System.out.println(greeting.get());  // Output: Hello World

Supplier<Double> random = () -> Math.random();
System.out.println(random.get());  // Output: Random number
```

#### **With Custom Interface:**
```java
@FunctionalInterface
interface Greeter {
    String greet();
}

Greeter greeter = () -> "Welcome!";
System.out.println(greeter.greet());  // Output: Welcome!
```

### **Key Points:**
- ✅ Empty parentheses `()` are **required**
- ✅ No parameters means no input needed
- ✅ Can still return a value
- ❌ Cannot omit `()` even if no parameters

---

## 3. Single Parameter Lambda

### **Syntax:**
```java
parameter -> expression
// OR
(parameter) -> expression
```

### **Special Rule:**
**Parentheses are OPTIONAL for single parameter!**

```java
// Both are valid:
x -> x * 2          // Without parentheses ✅
(x) -> x * 2        // With parentheses ✅
```

### **When to Use:**
- When functional interface method takes exactly one parameter
- Examples: `Predicate<T>`, `Function<T,R>`, `Consumer<T>`

### **Examples:**

#### **With Predicate:**
```java
// Single parameter, returns boolean
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4));  // true

// With parentheses (also valid)
Predicate<Integer> isPositive = (n) -> n > 0;
System.out.println(isPositive.test(5));  // true
```

#### **With Function:**
```java
// Single parameter, returns transformed value
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello"));  // 5

Function<Integer, Integer> square = x -> x * x;
System.out.println(square.apply(5));  // 25
```

#### **With Consumer:**
```java
// Single parameter, no return value
Consumer<String> printer = msg -> System.out.println(msg);
printer.accept("Hello");  // Output: Hello

Consumer<Integer> doubler = n -> System.out.println(n * 2);
doubler.accept(5);  // Output: 10
```

### **Type Inference:**
```java
// Type is inferred from context
Function<String, Integer> length = s -> s.length();

// Can explicitly specify type (rarely needed)
Function<String, Integer> length2 = (String s) -> s.length();
```

### **Key Points:**
- ✅ Parentheses optional for single parameter
- ✅ Type is automatically inferred
- ✅ Can explicitly specify type if needed
- ⚠️ If you specify type, parentheses are required: `(String s) -> ...`

---

## 4. Multiple Parameter Lambda

### **Syntax:**
```java
(param1, param2, ...) -> expression
```

### **Rules:**
- ✅ Parentheses are **REQUIRED**
- ✅ Parameters separated by commas
- ✅ Types are inferred (can be explicit)

### **When to Use:**
- When functional interface method takes 2+ parameters
- Examples: `BiFunction<T,U,R>`, `BiPredicate<T,U>`, `Comparator<T>`

### **Examples:**

#### **With BiFunction:**
```java
// Two parameters, returns result
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(5, 3));  // 8

BiFunction<String, String, String> concat = (s1, s2) -> s1 + s2;
System.out.println(concat.apply("Hello", "World"));  // HelloWorld
```

#### **With BiPredicate:**
```java
// Two parameters, returns boolean
BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
System.out.println(isGreater.test(5, 3));  // true

BiPredicate<String, String> startsWith = (str, prefix) -> str.startsWith(prefix);
System.out.println(startsWith.test("Hello", "He"));  // true
```

#### **With Comparator:**
```java
// Two parameters for comparison
Comparator<Integer> ascending = (a, b) -> a - b;
Comparator<String> byLength = (s1, s2) -> s1.length() - s2.length();

List<String> names = Arrays.asList("John", "Alice", "Bob");
names.sort(byLength);
System.out.println(names);  // [Bob, John, Alice]
```

#### **With Custom Interface:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b, int c);
}

Calculator sum = (a, b, c) -> a + b + c;
System.out.println(sum.calculate(1, 2, 3));  // 6
```

### **Explicit Types (Optional):**
```java
// Type inference (common)
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// Explicit types (rarely needed)
BiFunction<Integer, Integer, Integer> add2 = (Integer a, Integer b) -> a + b;
```

### **Key Points:**
- ✅ Parentheses **required** for multiple parameters
- ✅ Cannot omit parentheses
- ✅ All parameters must have types or none
- ❌ Cannot mix: `(int a, b)` is invalid
- ✅ Valid: `(a, b)` or `(int a, int b)`

---

## 5. Block Lambda

### **Syntax:**
```java
(parameters) -> {
    statement1;
    statement2;
    return result;  // if return type is not void
}
```

### **When to Use:**
- When you need multiple statements
- When you need local variables
- When logic is complex

### **Single Expression vs Block:**

| Feature | Expression Lambda | Block Lambda |
|---------|------------------|--------------|
| Syntax | `x -> x * 2` | `x -> { return x * 2; }` |
| Braces | Not needed | Required `{ }` |
| Return | Implicit | Explicit `return` needed |
| Statements | Single expression | Multiple statements |
| Use case | Simple logic | Complex logic |

### **Examples:**

#### **Simple Block:**
```java
// Expression lambda (single line)
Function<Integer, Integer> square1 = x -> x * x;

// Block lambda (equivalent)
Function<Integer, Integer> square2 = x -> {
    return x * x;
};
```

#### **Multiple Statements:**
```java
// Block with multiple statements
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

System.out.println(describe.apply(5));   // Positive
System.out.println(describe.apply(-3));  // Negative
```

#### **With Local Variables:**
```java
BiFunction<Integer, Integer, Integer> calculate = (a, b) -> {
    int sum = a + b;
    int product = a * b;
    int result = sum + product;
    return result;
};

System.out.println(calculate.apply(3, 4));  // 19 (7 + 12)
```

#### **With Loops:**
```java
Consumer<Integer> printTable = n -> {
    for (int i = 1; i <= 10; i++) {
        System.out.println(n + " x " + i + " = " + (n * i));
    }
};

printTable.accept(5);  // Prints multiplication table of 5
```

#### **Void Return Type:**
```java
// No return statement needed for void
Consumer<String> logger = msg -> {
    String timestamp = LocalDateTime.now().toString();
    System.out.println("[" + timestamp + "] " + msg);
};

logger.accept("Application started");
```

### **Key Points:**
- ✅ Use `{ }` for multiple statements
- ✅ Must use `return` keyword (if not void)
- ✅ Can declare local variables
- ✅ Can use loops, conditions
- ⚠️ Semicolon after each statement
- ⚠️ Don't forget `return` for non-void methods

---

## 6. Variable Capture

### **Definition:**
Lambda expressions can access variables from the enclosing scope (outer method or class).

### **What Can Be Captured:**

| Variable Type | Can Capture? | Example |
|--------------|--------------|---------|
| Local variables | ✅ Yes (if effectively final) | `int x = 5;` |
| Parameters | ✅ Yes (if effectively final) | `void method(int x)` |
| Instance variables | ✅ Yes (always) | `this.field` |
| Static variables | ✅ Yes (always) | `ClassName.field` |

### **Examples:**

#### **Capturing Local Variables:**
```java
public void example() {
    int multiplier = 10;  // Local variable
    
    Function<Integer, Integer> multiply = x -> x * multiplier;
    // Lambda captures 'multiplier' from outer scope
    
    System.out.println(multiply.apply(5));  // 50
}
```

#### **Capturing Method Parameters:**
```java
public Consumer<String> createGreeter(String prefix) {
    // Lambda captures 'prefix' parameter
    return name -> System.out.println(prefix + " " + name);
}

Consumer<String> greeter = createGreeter("Hello");
greeter.accept("John");  // Output: Hello John
```

#### **Capturing Instance Variables:**
```java
class Calculator {
    private int base = 100;  // Instance variable
    
    public Function<Integer, Integer> getAdder() {
        // Lambda captures 'base' instance variable
        return x -> x + base;
    }
}

Calculator calc = new Calculator();
Function<Integer, Integer> adder = calc.getAdder();
System.out.println(adder.apply(50));  // 150
```

#### **Capturing Static Variables:**
```java
class Config {
    static int MAX_VALUE = 1000;  // Static variable
    
    public static Predicate<Integer> getValidator() {
        // Lambda captures static variable
        return x -> x <= MAX_VALUE;
    }
}

Predicate<Integer> validator = Config.getValidator();
System.out.println(validator.test(500));  // true
```

### **Key Points:**
- ✅ Lambdas can access outer scope variables
- ✅ Instance and static variables can be modified
- ⚠️ Local variables must be effectively final
- ✅ Creates a closure over captured variables

---

## 7. Effectively Final Variables

### **Definition:**
A variable is **effectively final** if:
- It's not declared as `final`
- But its value is never changed after initialization
- Behaves as if it were `final`

### **Why This Matters:**
Lambdas can only capture local variables that are effectively final.

### **Valid Examples:**

#### **Effectively Final (Works):**
```java
public void example1() {
    int x = 10;  // Not declared final, but never changed
    
    Runnable r = () -> System.out.println(x);  // ✅ Works
    r.run();  // Output: 10
}
```

#### **Explicitly Final (Works):**
```java
public void example2() {
    final int x = 10;  // Explicitly final
    
    Runnable r = () -> System.out.println(x);  // ✅ Works
    r.run();  // Output: 10
}
```

### **Invalid Examples:**

#### **Modified Variable (Doesn't Work):**
```java
public void example3() {
    int x = 10;
    x = 20;  // ❌ Variable is modified
    
    Runnable r = () -> System.out.println(x);  // ❌ Compilation Error!
    // Error: Variable 'x' is accessed from within inner class, needs to be final or effectively final
}
```

#### **Modified After Lambda (Doesn't Work):**
```java
public void example4() {
    int x = 10;
    
    Runnable r = () -> System.out.println(x);  // Lambda captures x
    
    x = 20;  // ❌ Error: Cannot modify after capture
}
```

### **Why This Restriction Exists:**

**Reason:** Thread safety and predictability

```java
// If this were allowed (it's not):
int counter = 0;
Runnable task = () -> System.out.println(counter);

counter = 10;  // What value should lambda see?
new Thread(task).start();  // 0 or 10? Unpredictable!

counter = 20;  // Now what?
```

### **Workarounds:**

#### **1. Use Array (Mutable Container):**
```java
public void example() {
    int[] counter = {0};  // Array is effectively final
    
    Runnable increment = () -> {
        counter[0]++;  // ✅ Can modify array contents
        System.out.println(counter[0]);
    };
    
    increment.run();  // 1
    increment.run();  // 2
}
```

#### **2. Use AtomicInteger:**
```java
public void example() {
    AtomicInteger counter = new AtomicInteger(0);
    
    Runnable increment = () -> {
        System.out.println(counter.incrementAndGet());
    };
    
    increment.run();  // 1
    increment.run();  // 2
}
```

#### **3. Use Instance Variable:**
```java
class Counter {
    private int count = 0;  // Instance variable
    
    public Runnable getIncrementer() {
        return () -> {
            count++;  // ✅ Can modify instance variable
            System.out.println(count);
        };
    }
}
```

### **Comparison Table:**

| Variable Type | Can Modify in Lambda? | Reason |
|--------------|----------------------|---------|
| Local variable | ❌ No | Must be effectively final |
| Method parameter | ❌ No | Must be effectively final |
| Instance variable | ✅ Yes | Part of object state |
| Static variable | ✅ Yes | Shared class state |
| Array contents | ✅ Yes | Array reference is final, not contents |

### **Key Points:**
- ✅ Local variables must be effectively final
- ✅ "Effectively final" = never modified after initialization
- ❌ Cannot modify local variables in lambda
- ✅ Can modify instance/static variables
- ✅ Can modify array/object contents (not reference)
- ⚠️ Use wrappers (Array, AtomicInteger) if you need mutability

---

## 8. Interview Questions

### **Q1: What is the syntax of a lambda expression?**

**Answer:**
> "A lambda expression has the syntax `(parameters) -> expression` or `(parameters) -> { statements; }`. It consists of three parts: parameter list in parentheses, arrow operator (->), and body which can be a single expression or a block of statements. For example, `x -> x * 2` is a simple lambda that takes one parameter and returns its double."

### **Q2: When can you omit parentheses in lambda parameters?**

**Answer:**
> "You can omit parentheses only when there is exactly one parameter and you don't specify its type. For example, `x -> x * 2` is valid, but `(x, y) -> x + y` requires parentheses because there are multiple parameters. Also, if you specify the type like `(Integer x) -> x * 2`, parentheses are required."

### **Q3: What is the difference between expression lambda and block lambda?**

**Answer:**
> "An expression lambda has a single expression without braces, like `x -> x * 2`, where the return is implicit. A block lambda uses braces and can have multiple statements, like `x -> { int result = x * 2; return result; }`, where you must explicitly use the return keyword. Use expression lambdas for simple operations and block lambdas when you need multiple statements or local variables."

### **Q4: What does 'effectively final' mean?**

**Answer:**
> "A variable is effectively final if it's not declared as final but its value is never changed after initialization. Lambda expressions can only capture local variables that are effectively final. This restriction exists for thread safety and predictability. For example, `int x = 10;` followed by a lambda using x is valid, but if you later do `x = 20;`, it becomes a compilation error."

### **Q5: Can lambdas modify captured variables?**

**Answer:**
> "Lambdas cannot modify local variables or parameters from the enclosing scope - they must be effectively final. However, lambdas can modify instance variables, static variables, and the contents of arrays or objects (not the reference itself). If you need to modify a local variable, you can use workarounds like wrapping it in an array, using AtomicInteger, or making it an instance variable."

### **Q6: What variables can a lambda capture?**

**Answer:**
> "A lambda can capture four types of variables: local variables (must be effectively final), method parameters (must be effectively final), instance variables (can be modified), and static variables (can be modified). The restriction on local variables and parameters exists because they're stored on the stack and may not exist when the lambda executes later."

---

## Quick Reference Summary

### **Lambda Syntax Patterns:**

```java
// No parameters
() -> expression
() -> { statements; }

// Single parameter (parentheses optional)
x -> expression
(x) -> expression
x -> { statements; }

// Multiple parameters (parentheses required)
(x, y) -> expression
(x, y) -> { statements; }

// With explicit types
(Integer x) -> expression
(String s1, String s2) -> expression
```

### **Key Rules:**

| Rule | Description |
|------|-------------|
| `()` required | For zero or 2+ parameters |
| `()` optional | For single parameter (without type) |
| `{ }` required | For multiple statements |
| `return` required | In block lambda (if not void) |
| Effectively final | Local variables must not change |
| Type inference | Types usually inferred automatically |

### **Common Mistakes:**

❌ `x, y -> x + y` (missing parentheses for multiple params)  
❌ `(x) -> { x * 2 }` (missing return in block)  
❌ `x -> { return x * 2 }` (unnecessary braces for single expression)  
❌ Modifying captured local variables  

✅ `(x, y) -> x + y`  
✅ `(x) -> { return x * 2; }`  
✅ `x -> x * 2`  
✅ Using effectively final variables  

---

## Practice Exercises

Try to identify what's wrong with these lambdas:

1. `x, y -> x + y`
2. `(x) -> { x * 2 }`
3. `() -> return 5;`
4. `(int x, y) -> x + y`

**Answers:**
1. Missing parentheses: `(x, y) -> x + y`
2. Missing return: `(x) -> { return x * 2; }`
3. Unnecessary braces: `() -> 5`
4. Mixed types: `(int x, int y) -> x + y` or `(x, y) -> x + y`

---

**Remember:** Lambda syntax is designed to be concise while remaining readable. Choose the simplest form that clearly expresses your intent!