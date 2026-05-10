package project.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    public static class HealthResponse {
        public String status;

        public HealthResponse(String status) {
            this.status = status;
        }
    }

    @GetMapping("/health")
    public HealthResponse health() {
        return new HealthResponse("ok");
    }
}
