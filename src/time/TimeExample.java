package time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeExample {
    public static void main(String[] args) {

        // LocalDateTime : 타임존 없는 로컬 시
        // DB DATETIME, 로컬 이벤트 기록
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime : " + localDateTime);
        System.out.println("===================");


        // Instant : UTC 기준의 절대 시간
        // Kafka, Redis, 로그, API 타임스탬프
        Instant instant = Instant.now();
        System.out.println("Instant.now() ====> " + instant);
        System.out.println("===================");

        // System : 밀리초 단위 타임 스탬프
        // 성능 측정, 로그 기록
        long millis = System.currentTimeMillis();
        Instant instant2 = Instant.ofEpochMilli(millis);
        System.out.println("System.currentTimeMillis(): " + millis);
        System.out.println("Instant from millis: " + instant2);
        System.out.println("===================");

        // ZonedDateTime : 타임존이 포함된 시간
        // 다국적 서비스, 타임존 변환
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        System.out.println(ZonedDateTime.of(2025, 2, 14, 11, 14, 0, 0, ZoneId.of("Asia/Seoul")));
        System.out.println(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));
    }
}
