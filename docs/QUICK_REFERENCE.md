# Java 8 Quick Reference Guide

Quick lookup for common Java 8 patterns and syntax.

## 📋 Table of Contents
- [Lambda Syntax](#lambda-syntax)
- [Functional Interfaces](#functional-interfaces)
- [Stream Operations](#stream-operations)
- [Collectors](#collectors)
- [Method References](#method-references)

---

## Lambda Syntax

### Basic Forms
```java
// No parameters
() -> expression
() -> { statements; }

// Single parameter (parentheses optional)
x -> expression
(x) -> expression

// Multiple parameters (parentheses required)
(x, y) -> expression
(x, y) -> { statements; }
```

### Examples
```java
// Runnable
Runnable r = () -> System.out.println("Hello");

// Predicate
Predicate<Integer> isEven = n -> n % 2 == 0;

// BiFunction
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// Block lambda
Function<Integer, String> describe = n -> {
    if (n > 0) return "Positive";
    else return "Negative";
};
```

---

## Functional Interfaces

### Common Interfaces

| Interface | Parameters | Returns | Use Case |
|-----------|-----------|---------|----------|
| `Predicate<T>` | T | boolean | Test/filter |
| `Function<T,R>` | T | R | Transform |
| `Consumer<T>` | T | void | Action |
| `Supplier<T>` | none | T | Generate |
| `BiFunction<T,U,R>` | T, U | R | Combine |
| `BiPredicate<T,U>` | T, U | boolean | Test two |

### Examples
```java
// Predicate - test condition
Predicate<Integer> isPositive = n -> n > 0;
isPositive.test(5);  // true

// Function - transform
Function<String, Integer> length = s -> s.length();
length.apply("Hello");  // 5

// Consumer - perform action
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello");

// Supplier - provide value
Supplier<Double> random = () -> Math.random();
random.get();

// BiFunction - two inputs
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
add.apply(5, 3);  // 8
```

---

## Stream Operations

### Creating Streams
```java
// From collection
List<Integer> list = Arrays.asList(1, 2, 3);
list.stream()

// From values
Stream.of(1, 2, 3)

// From array
Arrays.stream(array)

// Range
IntStream.range(1, 10)
IntStream.rangeClosed(1, 10)

// Generate
Stream.generate(() -> Math.random()).limit(10)

// Iterate
Stream.iterate(0, n -> n + 2).limit(10)
```

### Intermediate Operations
```java
// filter - keep matching elements
.filter(n -> n > 5)

// map - transform elements
.map(n -> n * 2)

// flatMap - flatten nested structures
.flatMap(list -> list.stream())

// distinct - remove duplicates
.distinct()

// sorted - sort elements
.sorted()
.sorted(Comparator.reverseOrder())

// limit - take first n elements
.limit(10)

// skip - skip first n elements
.skip(5)

// peek - perform action without consuming
.peek(System.out::println)
```

### Terminal Operations
```java
// forEach - perform action on each
.forEach(System.out::println)

// collect - collect to collection
.collect(Collectors.toList())
.collect(Collectors.toSet())

// count - count elements
.count()

// reduce - combine elements
.reduce(0, (a, b) -> a + b)

// min/max - find minimum/maximum
.min(Comparator.naturalOrder())
.max(Comparator.naturalOrder())

// anyMatch/allMatch/noneMatch
.anyMatch(n -> n > 5)
.allMatch(n -> n > 0)
.noneMatch(n -> n < 0)

// findFirst/findAny
.findFirst()
.findAny()
```

---

## Collectors

### Basic Collectors
```java
// To List
.collect(Collectors.toList())

// To Set
.collect(Collectors.toSet())

// To Map
.collect(Collectors.toMap(
    keyMapper,
    valueMapper
))

// Joining strings
.collect(Collectors.joining())
.collect(Collectors.joining(", "))
.collect(Collectors.joining(", ", "[", "]"))
```

### Grouping & Partitioning
```java
// Group by
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));

// Group by with counting
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
    ));

// Partition by (boolean)
Map<Boolean, List<Integer>> partitioned = numbers.stream()
    .collect(Collectors.partitioningBy(n -> n > 5));
```

### Aggregation
```java
// Counting
.collect(Collectors.counting())

// Summing
.collect(Collectors.summingInt(Employee::getSalary))

// Averaging
.collect(Collectors.averagingDouble(Employee::getSalary))

// Statistics
.collect(Collectors.summarizingInt(Employee::getSalary))
```

---

## Method References

### Types of Method References

```java
// 1. Static method reference
Function<String, Integer> parser = Integer::parseInt;
// Equivalent: s -> Integer.parseInt(s)

// 2. Instance method on object
String prefix = "Hello, ";
Function<String, String> greeter = prefix::concat;
// Equivalent: s -> prefix.concat(s)

// 3. Instance method on parameter
Function<String, String> upper = String::toUpperCase;
// Equivalent: s -> s.toUpperCase()

// 4. Constructor reference
Supplier<ArrayList<String>> listMaker = ArrayList::new;
// Equivalent: () -> new ArrayList<>()
```

### Common Examples
```java
// Print each element
list.forEach(System.out::println);

// Convert to uppercase
list.stream().map(String::toUpperCase)

// Get length
list.stream().map(String::length)

// Parse integers
list.stream().map(Integer::parseInt)

// Create new objects
Stream.generate(Random::new)
```

---

## Common Patterns

### Find Duplicates
```java
Set<Integer> seen = new HashSet<>();
Set<Integer> duplicates = numbers.stream()
    .filter(n -> !seen.add(n))
    .collect(Collectors.toSet());
```

### Group By
```java
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

### Second Highest
```java
Optional<Integer> secondHighest = numbers.stream()
    .distinct()
    .sorted(Comparator.reverseOrder())
    .skip(1)
    .findFirst();
```

### Count Occurrences
```java
Map<Character, Long> charCount = text.chars()
    .mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(
        Function.identity(),
        Collectors.counting()
    ));
```

### List to Map
```java
Map<String, Integer> map = list.stream()
    .collect(Collectors.toMap(
        s -> s,              // key
        String::length       // value
    ));
```

---

## Tips & Tricks

### Effectively Final
```java
// Valid - never modified
int x = 10;
Runnable r = () -> System.out.println(x);

// Invalid - modified
int y = 10;
y = 20;  // Error!
Runnable r2 = () -> System.out.println(y);

// Workaround - use array
int[] counter = {0};
Runnable r3 = () -> counter[0]++;
```

### Parallel Streams
```java
// Sequential
list.stream().filter(...).map(...)

// Parallel
list.parallelStream().filter(...).map(...)
```

### Optional
```java
// Create
Optional<String> opt = Optional.of("value");
Optional<String> empty = Optional.empty();

// Check and get
if (opt.isPresent()) {
    String value = opt.get();
}

// Or else
String value = opt.orElse("default");
String value = opt.orElseGet(() -> "default");

// If present
opt.ifPresent(System.out::println);

// Map
opt.map(String::toUpperCase)
```

---

## Common Mistakes

❌ **Wrong:**
```java
// Missing parentheses for multiple params
x, y -> x + y

// Missing return in block
x -> { x * 2 }

// Modifying captured variable
int x = 10;
x = 20;
Runnable r = () -> System.out.println(x);
```

✅ **Correct:**
```java
// Parentheses for multiple params
(x, y) -> x + y

// Return in block
x -> { return x * 2; }

// Don't modify captured variable
int x = 10;
Runnable r = () -> System.out.println(x);
```

---

**Quick Reference Complete!**

For detailed explanations, see the theory files in `docs/theory/`