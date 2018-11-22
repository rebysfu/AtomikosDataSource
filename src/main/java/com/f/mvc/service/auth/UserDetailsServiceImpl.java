package com.f.mvc.service.auth;

import com.f.mvc.entity.auth.User;
import com.f.mvc.entity.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: bvsoo
 * Date: 2018/9/13
 * Time: 下午2:30
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String DEFAULT_ROLE_PREFIX = "ROLE_";

    @Autowired
    private UserService userService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * @param prefix
     * @param role
     * @return
     */
    private static String getRoleWithDefaultPrefix(String prefix, String role) {
        if (role == null) {
            return null;
        }
        if (prefix == null || prefix.length() == 0) {
            return role;
        }
        if (role.startsWith(prefix)) {
            return role;
        }
        return prefix + role;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByAccount(s);
        if (user == null) throw new UsernameNotFoundException("User name " + s + ",not found:");
        List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = userRoleList.stream().map(o -> new SimpleGrantedAuthority(getRoleWithDefaultPrefix(DEFAULT_ROLE_PREFIX, sysRoleService.findSysRoleById(o.getSysRoleId()).getRole()))).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(), grantedAuthorities);
    }
}
