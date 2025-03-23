package designPattern.behavior;

public class MainExample {

    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        System.out.println("************[strategy pattern]****************");
        paymentContext.setStrategy(new KakaoPay());
        paymentContext.execute(1000);

        paymentContext.setStrategy(new TossPay());
        paymentContext.execute(2000);

        System.out.println("************[state pattern]****************");
        PowerContext powerContext = new PowerContext();
        powerContext.setState(new On());
        powerContext.pressButton();

        powerContext.setState(new Off());
        powerContext.pressButton();

        System.out.println("************[state pattern]****************");
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton(); // ON!!!

        remote.setCommand(lightOff);
        remote.pressButton(); // OFF!!!
    }

}
