package lambda06;

public class OuterMain {
    private String message = "외부 클래스";

    public void execute() {
        // 1. 익명 클래스 예시 (1)
        Runnable anonymous = new Runnable() {
            private String message = "익명 클래스";

            @Override
            public void run() {
                // 익명 클래스에서 this는 익명 클래스의 인스턴스를 가리킴
                System.out.println("[익명 클래스] this: " + this);
                System.out.println("[익명 클래스] this.class: " + this.getClass());
                System.out.println("[익명 클래스] this.message: " + this.message);
            }
        };

        // 1. 익명 클래스 예시 (2)
        Runnable anonymous2 = new Runnable() {
            private String message = "익명 클래스";

            @Override
            public void run() {
                // 익명 클래스에서 this는 익명 클래스의 인스턴스를 가리킴
                System.out.println("[익명 클래스] this: " + this);
                System.out.println("[익명 클래스] this.class: " + this.getClass());
                System.out.println("[익명 클래스] this.message: " + this.message);
            }
        };

        // 2. 람다 예시
        Runnable lambda = () -> {
            // 람다에서의 this는 람다가 선언된 클래스의 인스턴스(즉, 외부 클래스) 가리킴
            System.out.println("[람다] this: " + this);
            System.out.println("[람다] this.class: " + this.getClass());
            System.out.println("[람다] this.message: " + this.message);
        };

        anonymous.run();
        System.out.println("---------------------------------");
        anonymous2.run();
        System.out.println("---------------------------------");
        lambda.run();
    }

    static void main(String[] args) {
        OuterMain outer = new OuterMain();
        System.out.println("[외부 클래스]: " + outer);
        System.out.println("---------------------------------");
        outer.execute();


        /*
        [외부 클래스]: lambda06.OuterMain@27716f4
        ---------------------------------
        [익명 클래스] this: lambda06.OuterMain$1@30f39991
        [익명 클래스] this.class: class lambda06.OuterMain$1
        [익명 클래스] this.message: 익명 클래스
        ---------------------------------
        [익명 클래스] this: lambda06.OuterMain$2@452b3a41
        [익명 클래스] this.class: class lambda06.OuterMain$2
        [익명 클래스] this.message: 익명 클래스
        ---------------------------------
        [람다] this: lambda06.OuterMain@27716f4
        [람다] this.class: class lambda06.OuterMain
        [람다] this.message: 외부 클래스
         */
    }
}
