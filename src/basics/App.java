
interface Add {

    int addition(int a, int b);
}

public class App {

    public static void main(String[] args) {

        // Lambda expression to add two numbers
        Add add = (a, b) -> a + b;
        // Using the lambda expression (no need to create a separate class
        int result = add.addition(10, 20);

        System.out.println("Sum: " + result);
    }
}