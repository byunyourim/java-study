package autoable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 문제 설명:
 *
 * "10대의 서버에 동시에 명령어를 실행하는 스크립트를 작성하세요.
 * 모든 서버에서 명령어 실행이 성공했는지 확인하고, 실패한 서버에 대해서는 최대 3번까지 재시도해야 합니다.
 * 성공 여부와 시도 횟수는 로그로 남겨야 합니다."
 *
 * 요구사항:
 * 비동기 처리 (멀티스레딩, 비동기 작업)
 * 실패 시 재시도 로직 구현 (최대 3회)
 * 실행 결과 로그 기록 (성공/실패 여부, 시도 횟수 포함)
 *
 * 예상 구현 방법:
 * Java의 ExecutorService 사용
 * 실패 처리 → 재시도 로직 작성
 * 로그 출력 → 파일 또는 콘솔 출력
 */

public class ImplementExample01 {

    public static void main(String[] args) {

    }

}


class AutoableCommand{

    private ExecutorService service = Executors.newScheduledThreadPool(10);

    public void startServer() {
        service.submit(() -> {

            executeCommand();
        });
    }

    public void executeCommand() {

    }

}
