package reybo.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reybo.account.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
@Slf4j
public class HealthController {

    private final AccountRepository accountRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> healthCheck() {
        log.info("🔍 [HEALTH] Health check requested");

        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("timestamp", LocalDateTime.now());
        healthInfo.put("service", "Account Service");

        try {
            long accountCount = accountRepository.count();
            healthInfo.put("database", "CONNECTED");
            healthInfo.put("accountsInDatabase", accountCount);
            log.info("✅ [HEALTH] Database connected, accounts: {}", accountCount);
        } catch (Exception e) {
            log.error("❌ [HEALTH] Database health check failed: {}", e.getMessage());
            healthInfo.put("database", "DISCONNECTED");
            healthInfo.put("error", e.getMessage());
        }

        return healthInfo;
    }

    @GetMapping("/readiness")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> readinessCheck() {
        log.info("🔍 [HEALTH] Readiness check requested");

        Map<String, Object> readinessInfo = new HashMap<>();
        readinessInfo.put("status", "READY");
        readinessInfo.put("timestamp", LocalDateTime.now());
        readinessInfo.put("service", "Account Service");

        log.info("✅ [HEALTH] Service is ready");
        return readinessInfo;
    }

    @GetMapping("/liveness")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> livenessCheck() {
        log.info("🔍 [HEALTH] Liveness check requested");

        Map<String, Object> livenessInfo = new HashMap<>();
        livenessInfo.put("status", "ALIVE");
        livenessInfo.put("timestamp", LocalDateTime.now());
        livenessInfo.put("service", "Account Service");

        log.info("✅ [HEALTH] Service is alive");
        return livenessInfo;
    }
}