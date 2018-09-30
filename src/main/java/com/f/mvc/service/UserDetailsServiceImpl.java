package com.f.mvc.service;

import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
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

    @Autowired
    private UserService userService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByAccount(s);
        if (user == null) throw new UsernameNotFoundException("User name " + s + ",not found:");
        List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = userRoleList.stream().map(o -> new SimpleGrantedAuthority(sysRoleService.findSysRoleById(o.getSysRoleId()).getRole())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(), grantedAuthorities);
    }
}
