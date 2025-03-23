package designPattern.behavior;

/**
 * Command pattern
 *
 * - 실행 기능을 캡슐화하여 주어진 여러 기능을 실행할 수 있는 재사용성 높은 설계 패턴
 * - command : 실행될 기능에 대한 인터페이스
 * - concreteCommand : 실제로 실행되는 기능을 구현
 * - Invoker : 기능의 실행을 요청하는 호출자 클래스 (RemoteControl)
 * - Receiver :  ConcreteCommand의 기능을 실행하기 위해 사용하는 수신자 클래스 (Light)
 *
 */
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}


interface Command {
    void execute();
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class Light {

    public void turnOn() {
        System.out.println("ON!!!");
    }

    public void turnOff() {
        System.out.println("OFF!!!");

    }

}

