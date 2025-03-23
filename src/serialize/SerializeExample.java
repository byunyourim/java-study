package serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 직렬화
        Person winnie = new Person("winnie", "010-1111-2222");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(winnie);
        oos.close();

        // 역직렬화
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Person deserialize = (Person) ois.readObject();
        ois.close();

        System.out.println(deserialize.name);
        System.out.println(deserialize.phone);
        System.out.println(deserialize.city);
        System.out.println(deserialize.weight);  // weight는 transient이므로 기본값(0) 으로 설정됨

    }
}

class Person implements Serializable {
    private static long serialVersionUID = 1L;
    String name;
    String phone;
    String city;
    transient int weight;

    Person (String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.city = "Seoul";
        this.weight = 100;
        System.out.println("생성자 호출!");
    }
}



