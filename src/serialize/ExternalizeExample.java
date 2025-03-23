package serialize;

import java.io.*;

public class ExternalizeExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PersonExternal p1 = new PersonExternal("Alice", 25, "password");
        // serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(p1);
        oos.close();

        // deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        PersonExternal p2 = (PersonExternal) ois.readObject();
        ois.close();

        System.out.println(p2);
    }
}

class PersonExternal implements Externalizable {
    private String name;
    private int age;
    private transient String password;

    public PersonExternal() {}

    public PersonExternal(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String) in.readObject();
        this.age = in.readInt();
        this.password = "origin_password";
    }

    @Override
    public String toString() {
        return "이름: " + name + ", 나이: " + age + ", 비밀번호: " + password;
    }
}


