import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) {

        // Sample data
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> names = Arrays.asList("aLice", "Bob", "charlie", "David", "Eve");
        Stream<String> stream = Stream.of(
                "Geeks", "fOr", "GEEKSQUIZ", "GeeksforGeeks");

        // Example 1: Filter even numbers and square them
        System.out.println("1. Even numbers squared:");
        numbers.stream()
                .filter(n -> n % 2 == 0) // Predicate (implicit)
                .map(n -> n * n) // Function (implicit)
                .forEach(System.out::println); // Consumer (implicit)

        names.stream()
                .filter(str -> Character.isUpperCase(str.charAt(0))).forEach(System.out::println);

        stream.filter(str -> str.endsWith("s"))
                .forEach(System.out::println);

    }
}
