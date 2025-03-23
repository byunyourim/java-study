package copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deep Copy
 * 1. 새로운 객체를 생성하여 기존 객체 복사
 *    - Copy Constructor (복사 생성자) 사용
 *    - 직접 clone()을 오버라이딩하여 Deep Copy 수행
 *    - Serialization을 이용한 복사
 * 2. 불변 컬렉션을 이용하여 변경 방지
 *    - Collections.unmodifiableList()
 *    - Collections.unmodifiableMap()
 *    - 가변 객체를 외부에서 변경할 수 없도록 감싸는 방법
 */
public class CopyExample {

    public static void main(String[] args) throws CloneNotSupportedException {

        List<String> hobbies = new ArrayList<>();
        hobbies.add("soccer");
        hobbies.add("baseball");

        Person1 p1 = new Person1("winnie1", hobbies);
        Person2 p2 = new Person2("winnie2", hobbies);

        // 원본 리스트에 movie 추가
        hobbies.add("movie");

        for (String h : hobbies) {
            System.out.println(h);
        }
        System.out.println("******************");

        for (String h : p1.getHobbies()) {
            System.out.println(h);
        }
        System.out.println("******************");

        for (String h : p2.getHobbies()) {
            System.out.println(h);
        }
        System.out.println("******************");
        Person person = new Person("yurim", new Address("busan"));
        Address address = new Address("Seoul");

        Person shallow = person.shallowCopy(address);   // shallow copy
        Person deep = person.deepCopy(address);         // deep copy

        address.setCity("Jeju");

        System.out.println(shallow.address.city);   // Jeju
        System.out.println(deep.address.city);      // Seoul

        System.out.println("*******clone method*******");
        Person alice = new Person("Alice", new Address("busan"));
        Person copyAlice = (Person) alice.clone();

        alice.name = "mimi";
        alice.address.city = "daejeon";

        System.out.println(alice.name);             // mimi
        System.out.println(copyAlice.name);         // Alice
        System.out.println(alice.address.city);     // mimi
        System.out.println(copyAlice.address.city); // daejeon      --> clone는 얕은 복사만 수행

    }
}

class Address {
    String city;

    Address (String city) {
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

class Person implements Cloneable{
    String name;
    Address address;

    Person(String name) {
        this.name = name;
    }

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // 앝은 복사
    public Person shallowCopy(Address address) {
        return new Person(this.name, address);
    }

    // 깊은 복사
    public Person deepCopy(Address address) {
        return new Person(this.name, new Address(address.city));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


// shallow copy
class Person1 {
    private String name;
    private List<String> hobbies;

    Person1(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = hobbies; // 참조를 복사
    }

    public List<String> getHobbies() {
        return this.hobbies;
    }
}

// deep copy
class Person2 {
    private String name;
    private List<String> hobbies;

    Person2(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = new ArrayList<>(hobbies);
    }

    public List<String> getHobbies() {
        return Collections.unmodifiableList(hobbies);
    }

}

