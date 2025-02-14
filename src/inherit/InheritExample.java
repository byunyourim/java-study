package inherit;

public class InheritExample {

    public static void main(String[] args) {

        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.speak();    // 멍!
        cat.speak();    // 야옹~

        dog.eat();  // 개껌 냠냠
        cat.eat();  // 냠냠

        ((Cat) cat).feature();  // 귀여움
        // cat.walk();

        Dog dog2 = new Dog();
        Cat cat2 = new Cat();

        dog2.eat(); // 개껌 냠냠
        cat2.eat(); // 냠냠

        cat2.sleep();   // 쿨쿨
        cat2.feature(); // 귀여움
        cat2.walk();    // 총총
    }
}
