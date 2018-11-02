package com.f.datasource;


import com.f.mvc.service.server.BrandTunnelRelationService;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-06 下午1:51
 **/
public class UserDetailsServiceTest extends ServiceBaseTest {
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private BrandTunnelRelationService brandTunnelRelationService;

    @Test
    public void loadUserByUsername() {
        userDetailsService.loadUserByUsername("admin");
    }
}
