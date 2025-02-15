package ratelimit.tokenbucket;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketExample {

    public static void main(String[] args) throws InterruptedException {
        TokenBucket rateLimiter = new TokenBucket(5, 2000);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    rateLimiter.takeToken();
                    System.out.println(Thread.currentThread().getName() + " - 요청 성공");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(500);
        }
        Thread.sleep(10000);
    }
}

class TokenBucket {
    private final int bucketSize;
    private final int durationMillis;
    private AtomicInteger tokenCount;
    private long lastMillisFillTime;

    TokenBucket(int bucketSize, int durationMillis) {
        this.bucketSize = bucketSize;
        this.durationMillis = durationMillis;
        this.tokenCount = new AtomicInteger(bucketSize);
        this.lastMillisFillTime = System.currentTimeMillis();
    }

    public void takeToken() throws InterruptedException {
        int originTokenCount = tokenCount.get();
        int tokenCountAfterUsed = originTokenCount - 1;
        if (originTokenCount <= bucketSize && originTokenCount > 0) {
            if (tokenCount.compareAndSet(originTokenCount, tokenCountAfterUsed)) {
                return;
            } else {
                Thread.sleep(100);
                takeToken();
            }
        } else {
            System.out.println("토큰 소진 후 대기");
            refill();
            takeToken();
        }
    }

    public void refill() {
        long nowMilis = System.currentTimeMillis();
        long timePassedMillis = nowMilis - lastMillisFillTime;

        if (timePassedMillis >= durationMillis) {
            tokenCount.set(bucketSize);
            lastMillisFillTime = nowMilis;
        }
    }
}
