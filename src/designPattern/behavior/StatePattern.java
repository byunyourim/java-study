package designPattern.behavior;

/**
 * [행위 패턴] 상태 패턴
 * - 객체의 내부 상태에 따라 행동을 변경해야 할 때, 상태를 객체로 분리해 관리하는 패턴.
 * - 상태가 바뀌면 객체의 동작도 바뀜
 * - 객체의 행동이 상태에 따라 달라질 때
 */
class StatePatternExample {
    public static void Main(String[] args) {



    }
}


interface PowerState {
    void handle();
}

class On implements PowerState {

    @Override
    public void handle() {
        System.out.println("ON!!!");
    }
}

class Off implements PowerState {

    @Override
    public void handle() {
        System.out.println("OFF!!!");
    }
}

class PowerContext {
    private PowerState state;

    public void setState(PowerState state) {
        this.state = state;
    }

    public void pressButton() {
        state.handle();
    }
}



