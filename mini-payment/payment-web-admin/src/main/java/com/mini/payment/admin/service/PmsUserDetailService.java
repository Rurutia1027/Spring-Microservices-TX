package com.mini.payment.admin.service;

import com.mini.payment.admin.domain.PmsUserDetail;
import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.utils.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class PmsUserDetailService implements UserDetailsService {
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // mock a username & password here
        // and also a set of authorities in set
        String password = "test";
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // here we need to load PmsUser from corresponding database tables
        PmsUserDetail pmsUserDetail = new PmsUserDetail(username, passwordEncoder.encode(password),
                authorities);
        return pmsUserDetail;
    }

    private Set<GrantedAuthority> getAuthoritiesForUser(PmsUser pmsUser) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // query user's permission and converted those permission into Authority items and
        // return
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
