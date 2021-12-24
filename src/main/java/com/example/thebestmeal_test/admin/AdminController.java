package com.example.thebestmeal_test.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService adminService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/adminposting")
    public AdminDto getAdminPosting() {
        return adminService.toAdminPosting();
    }
}
