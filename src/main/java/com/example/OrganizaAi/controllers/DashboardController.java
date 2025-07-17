package com.example.OrganizaAi.controllers;

import com.example.OrganizaAi.dto.DashboardDTO;
import com.example.OrganizaAi.security.TokenService;
import com.example.OrganizaAi.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    private final TokenService tokenService;

    public DashboardController(DashboardService dashboardService,
                               TokenService tokenService) {
        this.dashboardService = dashboardService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard(@RequestHeader("Authorization") String token,
                                                     @RequestParam int mes,
                                                     @RequestParam int ano) {
        String userId = tokenService.extractUserIdFromHeader(token);
        DashboardDTO dashboard = dashboardService.getDashboardData(userId, mes, ano);
        return ResponseEntity.ok(dashboard);
    }
}
