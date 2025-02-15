package ratelimit.tokenbucket;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketExample {

}

class TokenBucket {
    private final int bucketSize;
    private final int durationMillis;
    private final AtomicInteger tokenCount;

    TokenBucket(int bucketSize, int durationMillis) {
        this.bucketSize = bucketSize;
        this.durationMillis = durationMillis;
        this.tokenCount = new AtomicInteger(0);
    }

    public void takeToken() throws InterruptedException {
        int originTokenCount = tokenCount.get();
        int tokenCountAfterUsed = originTokenCount - 1;
        if (originTokenCount < bucketSize ) {
            if (tokenCount.compareAndSet(originTokenCount, tokenCountAfterUsed)) {
                return;
            } else {
                Thread.sleep(100);
                takeToken();
            }
        } else {
            refill();
            takeToken();
        }
    }

    public void refill() {
        // TODO
    }


}
