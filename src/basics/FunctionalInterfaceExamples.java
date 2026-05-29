import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunInt {
    public static void main(String[] args) {
Predicate<Integer> isEven = n -> n % 2 == 0;
Function<Integer, Integer> square = n -> n * n;
Function<String, Integer> leg = n -> n.length();
Consumer<Integer> print = n -> System.out.println(n);
Supplier<String> greet = () -> "Hello";
Supplier<Integer> random = () -> (int) (Math.random() * 5);

System.out.println(isEven.test(4));
System.out.println(leg.apply("Hello"));
System.out.println(square.apply(5));
print.accept(10);
System.out.println(greet.get());
System.out.println(random.get());
    
}}
