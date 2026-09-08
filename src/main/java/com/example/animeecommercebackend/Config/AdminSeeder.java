package com.example.animeecommercebackend.Config;

import com.example.animeecommercebackend.Entity.Enums.UserRole;
import com.example.animeecommercebackend.Entity.Enums.UserStatus;
import com.example.animeecommercebackend.Entity.User;
import com.example.animeecommercebackend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Bean
    public CommandLineRunner createAdmin(){
        return args -> {
            if(userRepository.findByEmail(adminEmail).isEmpty()){
                User admin = new User();

                admin.setEmail("");
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setFirstName("Sum");
                admin.setLastName("Panha");
                admin.setPhoneNumber("068488014");
                admin.setUsername("nhazz6767");
                admin.setStatus(UserStatus.ACTIVE);
                admin.setRole(UserRole.ADMIN);

                userRepository.save(admin);
            }
        };
    }

}
