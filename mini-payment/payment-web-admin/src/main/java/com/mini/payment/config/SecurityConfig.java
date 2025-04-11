package com.mini.payment.config;

import com.mini.payment.admin.service.PmsUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PmsUserDetailService pmsUserDetailService(PasswordEncoder encoder) {
        PmsUserDetailService pmsUserDetailService = new PmsUserDetailService();
        pmsUserDetailService.setPasswordEncoder(encoder);
        return pmsUserDetailService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder encoder,
                                                       PmsUserDetailService pmsUserDetailService) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder
                .userDetailsService(pmsUserDetailService)
                .passwordEncoder(encoder);
        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PmsUserDetailService pmsUserDetailService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/hello/**").authenticated()
                        .anyRequest().permitAll())
                .userDetailsService(pmsUserDetailService)
                .formLogin(form -> form
                        .permitAll());

        return http.build();
    }

//    @Bean
//    public UserDetailsService pmsUserDetailsService(PasswordEncoder encoder) {
////        UserDetails user = User.builder()
////                .username("test")
////                .password(encoder.encode("test"))
////                .roles("ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(user);
//        return new PmsUseSer
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
