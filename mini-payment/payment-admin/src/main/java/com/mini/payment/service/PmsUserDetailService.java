package com.mini.payment.service;

import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.service.PmsPermissionService;
import com.mini.payment.permission.service.PmsRolePermissionService;
import com.mini.payment.permission.service.PmsRoleService;
import com.mini.payment.permission.service.PmsUserRoleService;
import com.mini.payment.permission.service.PmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PmsUserDetailService implements UserDetailsService {
    @Autowired
    private PmsUserService pmsUserService;

    @Autowired
    private PmsRoleService pmsRoleService;

    @Autowired
    private PmsPermissionService pmsPermissionService;

    @Autowired
    private PmsUserRoleService pmsUserRoleService;

    @Autowired
    private PmsRolePermissionService pmsRolePermissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PmsUser pmsUser = pmsUserService.getByLoginName(username);
        if (Objects.isNull(pmsUser)) {
            throw new UsernameNotFoundException("User login name not found!");
        }

        List<String> permissionNameList =
                pmsPermissionService.findPermissionsByUserId(pmsUser.getId());

        // convert permission names into Spring Security authority items
        List<GrantedAuthority> authorities = permissionNameList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());

        // return db loaded username & encoded password, and the user's associated authorities
        return new User(pmsUser.getLoginName(), pmsUser.getLoginPwd(),
                authorities);
    }
}
