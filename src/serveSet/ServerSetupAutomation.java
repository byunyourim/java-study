package serveSet;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.*;

public class ServerSetupAutomation {

    private static final int SERVER_COUNT = 20;
    private static final String START_IP = "192.168.1.";
    private static final String SLACK_WEBHOOK_URL = "https://hooks.slack.com/services/your/slack/webhook";
    private static final String LOG_FILE = "/var/log/setup.log";
    private static final String AUTHORIZED_KEY = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC..."; // SSH 키

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5); // 병렬 실행을 위한 스레드 풀

        for (int i = 1; i <= SERVER_COUNT; i++) {
            int serverId = i;
            executor.submit(() -> setupServer(serverId));
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.HOURS);
        log("모든 서버 설정 완료");
    }

    private static void setupServer(int serverId) {
        String serverName = String.format("infra-server-%02d", serverId);
        String serverIp = START_IP + (100 + serverId);

        try {
            // 1. /etc/hosts 파일에 서버 정보 등록
            Files.write(Paths.get("/etc/hosts"),
                String.format("%s %s\n", serverIp, serverName).getBytes(),
                StandardOpenOption.APPEND);
            log(serverName + " IP 등록 완료");

            // 2. 필수 패키지 설치
            executeCommand("sudo apt-get update");
            executeCommand("sudo apt-get install -y nginx docker.io python3");
            log(serverName + " 필수 패키지 설치 완료");

            // 3. SSH 접근 설정
            Files.write(Paths.get("/home/ubuntu/.ssh/authorized_keys"),
                (AUTHORIZED_KEY + "\n").getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            executeCommand("sudo ufw allow 22/tcp");
            executeCommand("sudo ufw enable");
            log(serverName + " SSH 접근 설정 완료 (22번 포트 허용)");

            // 4. 디스크 사용량 체크 및 Slack 경고
            String usage = executeCommand("df / | awk 'NR==2 {print $5}'").replace("%", "").trim();
            int diskUsage = Integer.parseInt(usage);
            if (diskUsage >= 80) {
                sendSlackAlert("⚠️ " + serverName + " 디스크 사용량 경고: " + diskUsage + "% 사용 중!");
                log("경고: " + serverName + " 디스크 사용량 " + diskUsage + "%");
            }

        } catch (IOException | InterruptedException e) {
            log(serverName + " 설정 중 에러 발생: " + e.getMessage());
        }
    }

    private static String executeCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
        }

        process.waitFor();
        return result.toString();
    }

    private static void sendSlackAlert(String message) {
        try {
            ProcessBuilder builder = new ProcessBuilder("curl", "-X", "POST", "-H", "Content-type: application/json",
                "--data", "{\"text\": \"" + message + "\"}", SLACK_WEBHOOK_URL);
            builder.start().waitFor();
        } catch (IOException | InterruptedException e) {
            log("Slack 전송 실패: " + e.getMessage());
        }
    }

    private static synchronized void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(new Date() + " - " + message + "\n");
        } catch (IOException e) {
            System.err.println("로그 기록 중 에러: " + e.getMessage());
        }
    }
}
