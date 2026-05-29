# Functional Programming - Interview Guide

## Table of Contents
1. [Imperative vs Declarative Programming](#1-imperative-vs-declarative-programming)
2. [Functional Programming Basics](#2-functional-programming-basics)
3. [Pure Functions](#3-pure-functions)
4. [Stateless Behavior](#4-stateless-behavior)
5. [Side Effects](#5-side-effects)
6. [Interview Questions & Answers](#6-interview-questions--answers)

---

## 1. Imperative vs Declarative Programming

### **Imperative Programming**
- **Focus:** HOW to do something (step-by-step instructions)
- **Style:** Describes the control flow explicitly
- **Uses:** Loops, conditions, state changes
- **Analogy:** Giving driving directions - "Turn left, go 2 blocks, turn right"

### **Declarative Programming**
- **Focus:** WHAT you want to achieve (the result)
- **Style:** Describes the desired outcome, not the steps
- **Uses:** Expressions and transformations
- **Analogy:** Saying "Take me to the airport" (GPS figures out how)

### **Example:**

**Task:** Get even numbers and square them

**Imperative (Before Java 8):**
```java
List<Integer> result = new ArrayList<>();
for (int i = 0; i < numbers.size(); i++) {
    int num = numbers.get(i);
    if (num % 2 == 0) {
        result.add(num * num);
    }
}
```

**Declarative (Java 8+):**
```java
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .collect(Collectors.toList());
```

### **Key Differences:**

| Aspect | Imperative | Declarative |
|--------|-----------|-------------|
| Focus | HOW (process) | WHAT (result) |
| Code | More verbose | More concise |
| Readability | Lower | Higher |
| Maintainability | Harder | Easier |
| Example | for loops | Stream API |

---

## 2. Functional Programming Basics

### **Definition:**
Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing state and mutable data.

### **Core Principles:**

#### **1. Functions are First-Class Citizens**
Functions can be:
- Assigned to variables
- Passed as arguments to other functions
- Returned from other functions

```java
// Assign to variable
Function<Integer, Integer> square = x -> x * x;

// Pass as argument
list.stream().map(square).collect(Collectors.toList());

// Return from function
Function<Integer, Integer> createMultiplier(int factor) {
    return x -> x * factor;
}
```

#### **2. Immutability**
- Data doesn't change after creation
- Create new data instead of modifying existing
- Prevents bugs from unexpected state changes

```java
// BAD (Mutable)
list.add("new item");  // Modifies original

// GOOD (Immutable)
List<String> newList = Stream.concat(list.stream(), Stream.of("new item"))
    .collect(Collectors.toList());  // Creates new list
```

#### **3. Higher-Order Functions**
Functions that:
- Take other functions as parameters
- Return functions as results

Examples: `map`, `filter`, `reduce`

```java
// map is a higher-order function
numbers.stream()
    .map(n -> n * 2)  // Takes function as parameter
    .collect(Collectors.toList());
```

#### **4. No Side Effects**
Functions should:
- Not modify external state
- Only depend on input parameters
- Always return the same output for the same input

---

## 3. Pure Functions

### **Definition:**
A pure function is a function that:
1. **Always returns the same output for the same input** (Deterministic)
2. **Has no side effects** (doesn't modify external state)
3. **Doesn't depend on external state** (only on input parameters)

### **Pure Function Example:**
```java
// PURE - Always returns same result
public int add(int a, int b) {
    return a + b;
}

add(2, 3);  // Always returns 5
add(2, 3);  // Always returns 5
add(2, 3);  // Always returns 5
```

### **Impure Function Example:**
```java
// IMPURE - Depends on external state
private int counter = 0;

public int increment(int value) {
    counter++;  // Modifies external state
    return value + counter;  // Result depends on counter
}

increment(5);  // Returns 6
increment(5);  // Returns 7 (different result!)
increment(5);  // Returns 8 (different result!)
```

### **Benefits of Pure Functions:**

| Benefit | Explanation |
|---------|-------------|
| **Testability** | Easy to test - just check input/output |
| **Predictability** | Same input always gives same output |
| **Caching** | Can cache results (memoization) |
| **Parallelization** | Safe to run in parallel (no shared state) |
| **Debugging** | Easier to debug - no hidden dependencies |
| **Reasoning** | Easy to understand what function does |

### **Pure vs Impure Comparison:**

| Aspect | Pure Function | Impure Function |
|--------|--------------|-----------------|
| Output | Deterministic | Non-deterministic |
| State | No external state | Uses external state |
| Side effects | None | Has side effects |
| Testing | Easy | Difficult |
| Thread-safe | Yes | No |

---

## 4. Stateless Behavior

### **Definition:**
- **Stateless:** Function doesn't maintain or depend on internal state
- **Stateful:** Function maintains internal state between calls

### **Stateless Example:**
```java
public class StatelessCalculator {
    public int add(int a, int b) {
        return a + b;  // No state, just computation
    }
}

StatelessCalculator calc = new StatelessCalculator();
calc.add(5, 3);  // Returns 8
calc.add(5, 3);  // Returns 8 (same result)
calc.add(5, 3);  // Returns 8 (always same)
```

### **Stateful Example:**
```java
public class StatefulCalculator {
    private int total = 0;  // Internal state
    
    public int add(int value) {
        total += value;  // Depends on previous state
        return total;
    }
}

StatefulCalculator calc = new StatefulCalculator();
calc.add(5);  // Returns 5
calc.add(3);  // Returns 8 (remembers previous call)
calc.add(2);  // Returns 10 (accumulates)
```

### **Comparison:**

| Aspect | Stateless | Stateful |
|--------|-----------|----------|
| Memory | No memory of past | Remembers past calls |
| Predictability | Highly predictable | Less predictable |
| Thread-safety | Thread-safe | Not thread-safe |
| Testing | Easy to test | Harder to test |
| Use case | Calculations, transformations | Counters, accumulators |

### **When to Use:**

**Use Stateless:**
- ✅ Mathematical calculations
- ✅ Data transformations
- ✅ Validation logic
- ✅ Multi-threaded environments

**Use Stateful:**
- ✅ Counters and accumulators
- ✅ Session management
- ✅ Caching
- ✅ Connection pools

---

## 5. Side Effects

### **Definition:**
A side effect is any observable change that occurs outside the function's scope.

### **Common Side Effects:**

1. **Modifying external variables**
   ```java
   int counter = 0;
   public void increment() {
       counter++;  // Side effect!
   }
   ```

2. **Modifying input parameters**
   ```java
   public void addToList(List<String> list) {
       list.add("item");  // Side effect!
   }
   ```

3. **I/O Operations**
   ```java
   public void process() {
       System.out.println("Processing");  // Side effect!
       // Writing to file, database, network
   }
   ```

4. **Throwing exceptions**
   ```java
   public int divide(int a, int b) {
       if (b == 0) throw new Exception();  // Side effect!
       return a / b;
   }
   ```

5. **Calling other functions with side effects**
   ```java
   public void doWork() {
       logToFile();  // Side effect if logToFile has side effects
   }
   ```

### **Function Without Side Effects:**
```java
public int calculateDiscount(int price, double percent) {
    return (int) (price * (1 - percent / 100));
    // Pure calculation, no side effects
}
```

### **Function With Side Effects:**
```java
private List<String> log = new ArrayList<>();

public int calculateDiscount(int price, double percent) {
    int result = (int) (price * (1 - percent / 100));
    log.add("Calculated: " + result);  // Side effect: modifies log
    System.out.println("Done");         // Side effect: console output
    return result;
}
```

### **Managing Side Effects:**

**Functional Programming Approach:**
- ✅ Minimize side effects
- ✅ Isolate side effects to specific places
- ✅ Make side effects explicit
- ✅ Keep core logic pure

**Example:**
```java
// Pure function (no side effects)
public int calculateTotal(List<Integer> prices) {
    return prices.stream().mapToInt(Integer::intValue).sum();
}

// Side effects isolated in separate function
public void processAndLog(List<Integer> prices) {
    int total = calculateTotal(prices);  // Pure
    System.out.println("Total: " + total);  // Side effect isolated here
}
```

---

## 6. Interview Questions & Answers

### **Q1: What is functional programming?**

**Answer:**
> "Functional programming is a programming paradigm where functions are first-class citizens, meaning they can be assigned to variables, passed as arguments, and returned from other functions. It emphasizes immutability, where data doesn't change after creation, and pure functions that have no side effects and always return the same output for the same input. The key benefit is that it makes code more predictable, testable, and maintainable by avoiding shared state and side effects."

**Key Points to Mention:**
- Functions as first-class citizens
- Immutability
- Pure functions (no side effects)
- Declarative style (WHAT vs HOW)
- Benefits: testability, maintainability, parallelization

---

### **Q2: Why were lambdas introduced in Java?**

**Answer:**
> "Lambdas were introduced in Java 8 to enable functional programming in Java and reduce boilerplate code. Before Java 8, we had to use verbose anonymous inner classes for simple operations. Lambdas make code more concise and readable. They also enabled the Stream API, which allows for efficient data processing and better support for parallel operations. This made Java more competitive with modern languages like Scala and Kotlin, and improved developer productivity."

**Key Points to Mention:**
- Reduce boilerplate (anonymous classes → lambdas)
- Enable functional programming
- Support Stream API
- Better parallel processing
- Improved code readability
- Make Java modern and competitive

**Example to Give:**
```java
// Before Java 8 (5 lines)
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// After Java 8 (1 line)
Runnable r = () -> System.out.println("Hello");
```

---

### **Q3: What's the difference between imperative and declarative programming?**

**Answer:**
> "Imperative programming focuses on HOW to do something by providing step-by-step instructions using loops and conditions. Declarative programming focuses on WHAT you want to achieve, describing the desired outcome without specifying the control flow. For example, in imperative style, you'd use a for loop to filter and transform data. In declarative style with Java 8 Streams, you'd use filter() and map() to express what you want, and the framework handles how to do it."

**Example to Give:**
```java
// Imperative (HOW)
List<Integer> result = new ArrayList<>();
for (int i = 0; i < numbers.size(); i++) {
    if (numbers.get(i) % 2 == 0) {
        result.add(numbers.get(i) * 2);
    }
}

// Declarative (WHAT)
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .collect(Collectors.toList());
```

---

### **Q4: What is a pure function?**

**Answer:**
> "A pure function is a function that always returns the same output for the same input and has no side effects. It doesn't modify external state or depend on it - only on its input parameters. For example, a simple add function that takes two numbers and returns their sum is pure. Pure functions are beneficial because they're easy to test, can be cached, are thread-safe, and make code more predictable and easier to reason about."

**Example to Give:**
```java
// Pure function
public int add(int a, int b) {
    return a + b;  // Always returns same result
}

// Impure function
private int counter = 0;
public int addWithCounter(int a, int b) {
    counter++;  // Side effect: modifies external state
    return a + b + counter;  // Result depends on counter
}
```

---

### **Q5: What are side effects and why should we avoid them?**

**Answer:**
> "Side effects are any observable changes that occur outside a function's scope, such as modifying external variables, performing I/O operations, or changing input parameters. We should minimize side effects because they make code harder to test, debug, and reason about. They can cause unexpected behavior, especially in concurrent environments. In functional programming, we try to isolate side effects to specific places and keep core business logic pure, making the codebase more maintainable and reliable."

**Examples of Side Effects:**
- Modifying global variables
- Console output (System.out.println)
- File/database operations
- Network calls
- Modifying input parameters

---

## Quick Reference Summary

### **Key Concepts:**

| Concept | Definition | Example |
|---------|-----------|---------|
| **Imperative** | HOW to do it | `for` loops |
| **Declarative** | WHAT you want | `.filter().map()` |
| **Pure Function** | Same input → Same output, no side effects | `add(2, 3)` always returns 5 |
| **Stateless** | No memory of previous calls | Each call independent |
| **Side Effect** | Changes outside function | Modifying variables, I/O |
| **Immutability** | Data doesn't change | Create new instead of modify |
| **First-Class Function** | Functions as values | Can pass, return, assign |

### **Benefits of Functional Programming:**

✅ **Predictable** - Same input always gives same output  
✅ **Testable** - Easy to write unit tests  
✅ **Maintainable** - Easier to understand and modify  
✅ **Parallel** - Safe for concurrent execution  
✅ **Composable** - Easy to combine small functions  
✅ **Debuggable** - Fewer hidden dependencies  

### **Interview Tips:**

1. **Always give examples** - Don't just explain theory
2. **Compare old vs new** - Show before/after Java 8
3. **Mention benefits** - Why it matters in real projects
4. **Be practical** - Relate to real-world scenarios
5. **Show understanding** - Explain trade-offs

---

## Practice Questions

Test your understanding:

1. Is `System.out.println()` a side effect? Why?
2. Can a pure function call another pure function?
3. Why are pure functions thread-safe?
4. What's the difference between stateless and pure?
5. Give an example of when you'd use stateful behavior

**Answers:**
1. Yes - it modifies console output (external state)
2. Yes - calling pure functions doesn't create side effects
3. They don't share state, so no race conditions
4. Stateless = no internal state; Pure = no side effects + deterministic
5. Counters, session management, caching, connection pools

---

**Remember:** Functional programming is about writing predictable, testable, and maintainable code by treating functions as values and avoiding side effects!