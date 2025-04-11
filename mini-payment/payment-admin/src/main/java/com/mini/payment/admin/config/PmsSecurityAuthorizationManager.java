package com.mini.payment.admin.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

@Component("pmsSecurityAuthorizationManager")
public class PmsSecurityAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        // get current authentication instance
        Authentication auth = authentication.get();
        if (Objects.isNull(auth) || !auth.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        // custom logic: only allow users with "ROLE_MY_TEST_ADMIN" have access
        boolean hasAccess = auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains(
                        "ROLE_MY_TEST_ADMIN"));
        return new AuthorizationDecision(hasAccess);
    }
}
