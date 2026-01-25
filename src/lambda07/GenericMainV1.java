package lambda07;

public class GenericMainV1 {

    public static void main(String[] args) {
        StringFunction upperCase = s -> s.toUpperCase();
        String result1 = upperCase.apply("hello");
        System.out.println("result1 = " + result1);

        NumberFunction square = n -> n * n;
        Integer result2 = square.apply(3);
        System.out.println("result2 = " + result2);

        // generic 사용
        GenericFunction<String, String> generic1 = s -> s.toUpperCase();
        GenericFunction<Integer, Integer> generic2 = n -> n * n;
    }

    @FunctionalInterface
    interface StringFunction {
        String apply(String s);
    }

    @FunctionalInterface
    interface NumberFunction {
        Integer apply(Integer s);
    }

    @FunctionalInterface
    interface GenericFunction<T, R> {
        R apply(T t);
    }
}
