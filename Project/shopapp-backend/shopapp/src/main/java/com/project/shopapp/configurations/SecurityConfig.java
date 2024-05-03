package com.project.shopapp.configurations;

import com.project.shopapp.models.User;
import com.project.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    // use os detail object (Đăng nhập vào hệ thống và sẽ quản lý đối tượng đăng nhập đó thông qua user is detail

    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber ->
//            Cho User kế thừa User Detail của Security
                userRepository.findByPhoneNumber(phoneNumber)
                        .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with phone number"));
    }

    // Đối tượng mã hóa mật khẩu mã về 256
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Max hóa (Có thể tự viết mã hóa)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
// Để tạo đc Authentication thì cần có userDetail và PasswordAnCoder
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
