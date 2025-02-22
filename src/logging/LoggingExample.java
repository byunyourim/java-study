package logging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoggingExample {

    public static void main(String[] args) throws InterruptedException {

        LogUtil util = new LogUtil();

        util.startScheduler();

        util.createLog("a".repeat(100));
        Thread.sleep(500);
        util.createLog("b".repeat(40));
        util.createLog("c".repeat(40));
        Thread.sleep(2500);
        util.createLog("d".repeat(30));
    }
}


class LogUtil {
    private List<String> logList = new ArrayList<>();  // 로그를 순서대로 저장할 리스트
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private long lastSentTime = System.currentTimeMillis();

    public void startScheduler() {


        scheduler.scheduleAtFixedRate(() -> {

            if (logList.isEmpty()) return;

            StringBuilder sentData = new StringBuilder();
            int currentDataLength = 0;

            synchronized (logList) {
                Iterator<String> iterator = logList.iterator();
                while(iterator.hasNext()) {
                    String log = iterator.next();

                    if (currentDataLength  + log.length() <= 100) {
                        if (currentDataLength > 0) {
                            sentData.append("||");
                        }

                        sentData.append(log);
                        currentDataLength += log.length();
                        iterator.remove();

                    } else {
                        break;
                    }
                }

            }
            if (sentData.length() > 0) {

                send(sentData.toString());
            }

        }, 0, 2, TimeUnit.SECONDS);
    }


    /**
     * Send
     * 로그를 비동기로 전송하는 함수
     */
    public void send(String data) {
            System.out.println("send data: " + data);

    }

    /**
     * 로그 추가
     * - 순서 유지
     */
    public void createLog(String data) {
        synchronized (this) {
            logList.add(data);
        }
    }

}
