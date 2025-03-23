package designPattern.behavior;


/**
 * [행위 패턴] Strategy Pattern
 * 상황에 따라서 전략을 바꾸는 설계 방식
 * - 동작을 실행 중에 바꿔야 할 경우
 *
 */
public class StrategyPatternExample {

}


interface PaymentStrategy {

    void pay(int amount);

}


class KakaoPay implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("kakao pay: " + amount);
    }
}



class TossPay implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("toss pay: " + amount);
    }
}

class PaymentContext {

    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(int amount) {
        strategy.pay(amount);
    }
}


