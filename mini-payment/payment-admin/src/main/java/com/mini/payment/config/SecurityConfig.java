package com.mini.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig    {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin123").roles("ADMIN");
//    }

//    @Bean
//    public MethodSecurityExpressionHandler expressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler =
//                new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//        return expressionHandler;
//    }
}
