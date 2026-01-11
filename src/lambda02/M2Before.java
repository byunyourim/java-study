package lambda02;

public class M2Before {
    public static void print1() {
        System.out.println("무게: 10kg");
    }
    public static void print2() {
        System.out.println("무게: 50kg");
    }
    public static void print3() {
        System.out.println("무게: 200g");
    }
    public static void print4() {
        System.out.println("무게: 40g");
    }

    public static void print(int weight, String unit) {
        System.out.println("무게: " + weight + unit);
    }

    public static void main(String[] args) {
        print1();
        print2();
        print3();
        print4();

        print(10 , "kg");
        print(50 , "kg");
        print(200 , "g");
        print(40 , "g");
    }
}