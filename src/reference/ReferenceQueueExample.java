package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueExample {

    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference<Object> weakReference = new WeakReference<>(obj, referenceQueue);

        obj = null;

        System.gc();

        Thread.sleep(1000);

        WeakReference<?> collectedRef = (WeakReference<?>) referenceQueue.poll();

        if (collectedRef == null) {
            System.out.println("객체가 아직 살아있습니다.");

        } else {
            System.out.println("객체가 가비지 컬렉션에 의해 제거되었습니다.");

        }
    }
}
