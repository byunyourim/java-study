package inherit;

public class Dog extends Animal implements Walkable{

    public void speak() {
        System.out.println("멍!");
    }

    public void eat() {
        System.out.println("개껌 냠냠");
    }

    @Override
    public void walk() {
        System.out.println("wadada");
    }
}
