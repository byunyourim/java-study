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

/*
[익명 클래스와 람다의 용도]
1. 익명 클래스
    - 상태를 유지하거나 다중 메서드를 구현할 필요가 있는 경우
    - 기존 클래스 또는 인터페이스를 상속하거나 구현할 때
    - 복잡한 인터페이스 구현이 필요할 때
2. 람다
    - 상태를 유지할 필요가 없고, 간결함이 중요한 경우
    - 단일 메서드만 필요한 간단한 함수형 인터페이스 구현 시
    - 더 나은 성능(이 부분은 미미함)과 간결한 코드가 필요한 경우
 */
