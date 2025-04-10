package com.mini.payment.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class PermissionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            response.setStatus(HttpStatus.SC_FORBIDDEN);
            return;
        }

        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/api/admin")) {
            if (authentication.getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("admin"))) {
                response.setStatus(HttpStatus.SC_FORBIDDEN);
                response.getWriter().write("Access Defined: Admin permission required");
                return;
            }
        } else {
            if (authentication.getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("user"))) {
                response.setStatus(403);
                response.getWriter().write("Access Denied: User permission required");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
