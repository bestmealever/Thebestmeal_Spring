package com.example.thebestmeal_test.admin;

import com.example.thebestmeal_test.domain.PostingStatus;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Secured("ROLE_ADMIN")
    @PostMapping("/postingaccepted/{id}")
    public void updateAccept(@PathVariable Long id) {
        adminService.toUpdateAccept(id);
    }}




