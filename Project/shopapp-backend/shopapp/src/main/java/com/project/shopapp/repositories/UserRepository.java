package com.project.shopapp.repositories;

import com.project.shopapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
 // Kiểm tra user có phone number có tồn tại hay không
    boolean existsByPhoneNumber(String phoneNumber);
// Trả về 1 User hoặc null
    Optional<User> findByPhoneNumber(String phoneNumber);
}
