package inherit;

public class Cat extends Animal implements Walkable {

    public void speak() {
        System.out.println("야옹~");
    }

    public void feature() {
        System.out.println("귀여움");
    }

    @Override
    public void walk() {
        System.out.println("총총");
    }
}
