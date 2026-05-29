import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ")";
    }
}

public class AdvancedStreamExamples {
    public static void main(String[] args) {
        
        System.out.println("=== ADVANCED STREAM API EXAMPLES ===\n");
        
        // ========================================
        // 1. FIND DUPLICATE ELEMENTS
        // ========================================
        System.out.println("1. FIND DUPLICATE ELEMENTS:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6, 7, 3);
        
        // Method 1: Using Set to track seen elements
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = numbers.stream()
            .filter(n -> !seen.add(n))  // add() returns false if already exists
            .collect(Collectors.toSet());
        
        System.out.println("Duplicates: " + duplicates);
        
        // Method 2: Using frequency count
        Set<Integer> duplicates2 = numbers.stream()
            .filter(n -> Collections.frequency(numbers, n) > 1)
            .collect(Collectors.toSet());
        
        System.out.println("Duplicates (Method 2): " + duplicates2);
        
        // Method 3: Using groupingBy and counting
        List<Integer> duplicates3 = numbers.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        System.out.println("Duplicates (Method 3): " + duplicates3);
        
        // ========================================
        // 2. GROUP EMPLOYEES BY DEPARTMENT
        // ========================================
        System.out.println("\n2. GROUP EMPLOYEES BY DEPARTMENT:");
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 60000),
            new Employee("Charlie", "IT", 80000),
            new Employee("David", "Finance", 70000),
            new Employee("Eve", "HR", 65000),
            new Employee("Frank", "IT", 90000),
            new Employee("Grace", "Finance", 75000)
        );
        
        // Group by department
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        
        byDepartment.forEach((dept, empList) -> {
            System.out.println(dept + ":");
            empList.forEach(emp -> System.out.println("  - " + emp));
        });
        
        // Count employees per department
        System.out.println("\nEmployee count by department:");
        Map<String, Long> countByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment, 
                Collectors.counting()
            ));
        countByDept.forEach((dept, count) -> 
            System.out.println(dept + ": " + count + " employees"));
        
        // Average salary by department
        System.out.println("\nAverage salary by department:");
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println(dept + ": $" + String.format("%.2f", avg)));
        
        // ========================================
        // 3. FIND SECOND HIGHEST SALARY
        // ========================================
        System.out.println("\n3. FIND SECOND HIGHEST SALARY:");
        
        List<Integer> salaries = Arrays.asList(50000, 75000, 60000, 90000, 80000, 90000);
        
        // Method 1: Using distinct and sorted
        Optional<Integer> secondHighest = salaries.stream()
            .distinct()                    // Remove duplicates
            .sorted(Comparator.reverseOrder())  // Sort descending
            .skip(1)                       // Skip the highest
            .findFirst();                  // Get second
        
        secondHighest.ifPresent(salary -> 
            System.out.println("Second highest salary: $" + salary));
        
        // Method 2: For employees
        Optional<Double> secondHighestEmpSalary = employees.stream()
            .map(Employee::getSalary)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst();
        
        secondHighestEmpSalary.ifPresent(salary -> 
            System.out.println("Second highest employee salary: $" + salary));
        
        // Get employee with second highest salary
        System.out.println("\nEmployee with second highest salary:");
        employees.stream()
            .filter(emp -> emp.getSalary() == secondHighestEmpSalary.orElse(0.0))
            .forEach(System.out::println);
        
        // ========================================
        // 4. COUNT OCCURRENCES OF EACH CHARACTER
        // ========================================
        System.out.println("\n4. COUNT OCCURRENCES OF EACH CHARACTER:");
        
        String text = "hello world";
        
        // Method 1: Using groupingBy and counting
        Map<Character, Long> charCount = text.chars()
            .mapToObj(c -> (char) c)
            .filter(c -> !Character.isWhitespace(c))  // Ignore spaces
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            ));
        
        System.out.println("Character frequencies:");
        charCount.forEach((ch, count) -> 
            System.out.println("'" + ch + "': " + count));
        
        // Method 2: Find most frequent character
        Optional<Map.Entry<Character, Long>> mostFrequent = charCount.entrySet().stream()
            .max(Map.Entry.comparingByValue());
        
        mostFrequent.ifPresent(entry -> 
            System.out.println("\nMost frequent character: '" + 
                entry.getKey() + "' appears " + entry.getValue() + " times"));
        
        // Method 3: Count specific character
        String word = "programming";
        long countM = word.chars()
            .filter(c -> c == 'm')
            .count();
        System.out.println("\nCount of 'm' in 'programming': " + countM);
        
        // ========================================
        // 5. CONVERT LIST TO MAP
        // ========================================
        System.out.println("\n5. CONVERT LIST TO MAP:");
        
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        
        // Method 1: Map with index as key
        Map<Integer, String> fruitMap1 = IntStream.range(0, fruits.size())
            .boxed()
            .collect(Collectors.toMap(
                i -> i,              // Key: index
                fruits::get          // Value: fruit name
            ));
        
        System.out.println("Map with index as key:");
        fruitMap1.forEach((key, value) -> 
            System.out.println(key + " -> " + value));
        
        // Method 2: Map with name as key, length as value
        Map<String, Integer> fruitMap2 = fruits.stream()
            .collect(Collectors.toMap(
                fruit -> fruit,           // Key: fruit name
                String::length            // Value: length
            ));
        
        System.out.println("\nMap with name as key, length as value:");
        fruitMap2.forEach((key, value) -> 
            System.out.println(key + " -> " + value));
        
        // Method 3: Employee list to map (ID -> Employee)
        Map<String, Employee> employeeMap = employees.stream()
            .collect(Collectors.toMap(
                Employee::getName,        // Key: name
                emp -> emp                // Value: employee object
            ));
        
        System.out.println("\nEmployee map (Name -> Employee):");
        employeeMap.forEach((name, emp) -> 
            System.out.println(name + " -> " + emp));
        
        // Method 4: Handle duplicate keys
        List<String> duplicateFruits = Arrays.asList("Apple", "Banana", "Apple", "Cherry");
        Map<String, Integer> fruitCountMap = duplicateFruits.stream()
            .collect(Collectors.toMap(
                fruit -> fruit,           // Key
                fruit -> 1,               // Initial value
                Integer::sum              // Merge function for duplicates
            ));
        
        System.out.println("\nFruit count map (handling duplicates):");
        fruitCountMap.forEach((fruit, count) -> 
            System.out.println(fruit + " -> " + count));
        
        // Method 5: Department -> List of employees
        Map<String, List<String>> deptEmployeeNames = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.toList())
            ));
        
        System.out.println("\nDepartment -> Employee names:");
        deptEmployeeNames.forEach((dept, names) -> 
            System.out.println(dept + " -> " + names));
    }
}

// Made with Bob
