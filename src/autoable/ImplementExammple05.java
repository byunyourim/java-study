package autoable;

/**
 * 문제 설명:
 *
 * "특정 서비스에서 발생하는 요청 수를 실시간으로 수집하고, 1분 단위로 데이터를 집계해 출력하는 시스템을 구현하세요.
 * 수집된 데이터를 시간별로 정리하고, 5분 주기로 출력해야 합니다."
 *
 * 요구사항:
 *
 * 실시간 데이터 수집 및 저장
 * 데이터 집계 (1분 단위로 요청 수 기록)
 * 5분 주기로 데이터를 정리해 출력
 * 예상 구현 방법:
 *
 * 데이터 수집 → Queue나 ConcurrentHashMap 사용
 * 스케줄러 → ScheduledExecutorService 사용
 * 집계 → 1분마다 데이터 수집 및 5분마다 출력
 */

public class ImplementExammple05 {

}
