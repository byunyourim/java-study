package autoable;


/**
 * 문제 설명:
 *
 * "특정 서버의 상태를 모니터링하는 기능을 구현하세요.
 * 서버의 CPU 사용률이 90% 이상인 경우 슬랙(Slack)으로 경고 메시지를 전송해야 합니다.
 * 경고 발생 후에도 5분 이내에 CPU 사용률이 줄어들지 않으면 서버 재시작 명령어를 실행해야 합니다."
 *
 * 요구사항:
 *
 * 주기적인 서버 상태 체크 (10초마다 실행)
 * 조건에 따른 알림 전송 (Slack Webhook API)
 * 서버 재시작 명령어 실행 (명령어 실행 시 로깅)
 * 예상 구현 방법:
 *
 * 시스템 상태 확인 → Java의 OperatingSystemMXBean 사용
 * 슬랙 연동 → Slack API 사용
 * 명령어 실행 → Runtime.getRuntime().exec() 사용
 */

public class ImplementExammple03 {

}
