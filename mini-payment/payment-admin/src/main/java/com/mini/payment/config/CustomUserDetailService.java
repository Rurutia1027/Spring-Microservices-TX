package com.mini.payment.config;

import com.mini.payment.permission.service.PmsPermissionService;
import com.mini.payment.permission.service.PmsUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private PmsUserService pmsUserService;

    @Autowired
    private PmsPermissionService pmsPermissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.equals(username, "mae")) {
            return new User("mae", passwordEncoder.encode("admin"),
                    AuthorityUtils.createAuthorityList("admin", "xxx", "MY_TEST_ADMIN"));
        }
        return null;
    }
}
