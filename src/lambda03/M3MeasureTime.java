package lambda03;

public class M3MeasureTime {

    public void measure(Procedure p) {
        long start = System.nanoTime();
        p.run();
        long end = System.nanoTime();

        System.out.println("실행시간: " + (end - start) + "ns");
    }



    public static void main(String[] args) {

    }
}