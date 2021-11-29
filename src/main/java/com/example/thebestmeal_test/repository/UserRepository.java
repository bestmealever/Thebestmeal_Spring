package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
