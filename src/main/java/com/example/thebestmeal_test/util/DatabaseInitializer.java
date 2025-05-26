package com.example.thebestmeal_test.util;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 기동 시 DB(스키마)가 없으면 CREATE DATABASE … 를 실행한다.
 *
 * - 대상 DB명·포트·비밀번호 등을 application.properties 에서 읽어옴
 * - @Profile("local") : 로컬 실행에서만 동작하도록 제한 (원하면 수정)
 */
@Slf4j
@Component
//@Profile("local")        // 👉 필요하면 dev/stage/prod 등으로 변경 or 삭제
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final Environment env;      // spring.datasource.* 값을 꺼내오기 용도

    @Override
    public void run(String... args) throws Exception {

        /* ── 1) 프로퍼티 가져오기 ───────────────────────────────────────── */
        String fullUrl  = env.getRequiredProperty("spring.datasource.url");
        String user     = env.getRequiredProperty("spring.datasource.username");
        String password = env.getRequiredProperty("spring.datasource.password");

        /* ── 2) URL 분해 → (베이스 URL, DB 이름) 구하기 ────────────────── */
        // 예: jdbc:mysql://localhost:3306/likedfoodtest?useSSL=false…
        // URI 파싱을 위해 "jdbc:" 접두어 제거
        URI uri = URI.create(fullUrl.substring(5));      // "mysql://localhost:3306/likedfoodtest?..."
        String dbName = uri.getPath().substring(1);      // likedfoodtest
        String baseUrl = "jdbc:mysql://" + uri.getHost() +
            ":" + (uri.getPort() == -1 ? 3306 : uri.getPort()) + "/";

        /* ── 3) DB(스키마) 존재하지 않으면 생성 ────────────────────────── */
        try (Connection conn = DriverManager.getConnection(baseUrl, user, password);
             Statement stmt  = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + dbName + "` " +
                "DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");

            log.info("✅ checked/created database: {}", dbName);
        }
    }
}