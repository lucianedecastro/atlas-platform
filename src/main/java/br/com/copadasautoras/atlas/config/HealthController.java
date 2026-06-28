package br.com.copadasautoras.atlas.config;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import org.springframework.boot.SpringBootVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("application", "Atlas");
        response.put("version", "0.1.0");
        response.put("status", "UP");
        response.put("springBoot", SpringBootVersion.getVersion());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(
                ApiResponse.success("Aplicação em execução.", response)
        );
    }

}
