package ratelimit.leakybucket;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [LeackyBucket]
 * 1. 요청이 들어오면 일정한 속도로 처리한다.
 * 2. 요청이 버킷의 크기만큼 담을 수 있다.
 * 3. 버킷의 크기를 넘게 되면 요청은 버려진다.
 */

public class LeackyBucketExample {

    public static void main(String[] args) throws InterruptedException {
        LeakyBucket rateLimiter = new LeakyBucket(5, 200);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                rateLimiter.addRequest();
            }).start();
            Thread.sleep(100);
        }

        Thread.sleep(1000);
        rateLimiter.executorShutdown();

        System.out.println("drop request : " + rateLimiter.getDropCount());
    }

}

class LeakyBucket {

    private final int bucketSize;
    private final long leakRate;
    private final Queue<Long> requestQueue;
    private final AtomicInteger dropCount;
    private final ScheduledExecutorService executor;


    LeakyBucket(int bucketSize, long leakRate) {
        this.bucketSize = bucketSize;
        this.leakRate = leakRate;
        this.requestQueue = new ConcurrentLinkedQueue<>();
        this.dropCount = new AtomicInteger(0);
        this.executor = Executors.newSingleThreadScheduledExecutor();

        startLeakScheduler();
    }

    public synchronized void addRequest() {
        if (requestQueue.size() >= bucketSize) {
            dropCount.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " - 버킷이 가득 찼습니다. (요청 Drop)");

            return;
        } else {
            requestQueue.add(System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " - 요청 추가되었습니다. (버킷 크기: " + requestQueue.size() + ")");
            return;
        }
    }

    public void startLeakScheduler() {
        executor.scheduleAtFixedRate(() -> {

            synchronized (this) {
                if (!requestQueue.isEmpty()) {
                    requestQueue.poll();
                    System.out.println("요청 처리되었습니다. (남은 요청: " + requestQueue.size() + ")");
                }
            }
        },0, leakRate, TimeUnit.MILLISECONDS);
    }

    public void executorShutdown() {
        executor.shutdown();
        System.out.println("Leaky Bucket 스케줄러 종료");
    }

    public int getDropCount() {
        return this.dropCount.get();
    }

}





