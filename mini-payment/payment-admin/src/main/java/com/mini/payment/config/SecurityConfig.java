package com.mini.payment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private AuthenticationSuccessHandler pmsAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler pmsAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PmsSecurityAuthorizationManager pmsSecurityAuthorizationManager;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setCreateTableOnStartup(false);
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/test", "/api/hello", "/api/sayHi").permitAll()
                        .requestMatchers("/api/account", "/api/admin", "/api/permission").authenticated()
                        .anyRequest().access(AuthorityAuthorizationManager.hasAnyAuthority(
                                "admin")))
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/auth")
                        .usernameParameter("user")
                        .passwordParameter("pass")
                        .successHandler(pmsAuthenticationSuccessHandler)
                        .failureHandler(pmsAuthenticationFailureHandler)
                        .permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/session.html")
                        .maximumSessions(3)
                        .maxSessionsPreventsLogin(false))
                .rememberMe(rem -> rem
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(60));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new CustomUserDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(List.of(authProvider));
    }
}

