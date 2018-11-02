package com.f.configure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码生成器注入
 * User: bvsoo
 * Date: 2018-05-03
 * Time: 11:31
 */

@Configuration
public class PasswordEncoderConfigurer {
    /**
     * 优先注入
     *
     * @return
     */
    @Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
