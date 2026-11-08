package com.booking.runner;

import com.booking.entity.Users;
import com.booking.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users user1 = new Users();
                user1.setUsername("admin");
                user1.setPassword(passwordEncoder.encode("admin")); // Securely store password
                user1.setRole("ROLE_ADMIN");

                userRepository.save(user1);
                System.out.println("Default admin user created!");
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                Users user2 = new Users();
                user2.setUsername("user");
                user2.setPassword(passwordEncoder.encode("user")); // Securely store password
                user2.setRole("ROLE.USER");

                userRepository.save(user2);
                System.out.println("Default user created!");
            }
        };
    }
}
