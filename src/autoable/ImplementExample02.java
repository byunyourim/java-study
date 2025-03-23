package autoable;

/**
 * 문제 설명:
 *
 * "외부 REST API로부터 주기적으로 데이터를 받아오는 서비스를 구현하세요.
 * API 요청은 5초마다 실행되며, 응답으로 받은 JSON 데이터를 파싱해 저장소에 저장해야 합니다.
 * 요청 실패 시, 에러 로그를 남기고 재시도해야 합니다."
 *
 * 요구사항:
 *
 * 주기적인 API 호출 (5초 간격)
 * JSON 응답 데이터 파싱
 * 실패 시 재시도 (최대 2회)
 * 에러 로깅
 * 예상 구현 방법:
 *
 * Java HttpClient 또는 Spring RestTemplate 사용
 * 응답 파싱 → Jackson 또는 Gson 라이브러리
 * 스케줄러 → ScheduledExecutorService 사용
 */
public class ImplementExample02 {

}
