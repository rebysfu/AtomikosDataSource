package com.f.configure.properties;

import com.google.common.base.Strings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/9/14
 * Time: 下午7:01
 */
@Data
@ConfigurationProperties(prefix = "spring.auth")
@Component
public class AuthConfigure implements Serializable {
    public static final String DEFAULT_ACCOUNT = "admin";
    public static final String DEFAULT_PASSWORD = "123456";
    private static final long serialVersionUID = -4671311372381362221L;
    private String account;
    private String password;


    public String getAccount() {
        return Strings.isNullOrEmpty(account) ? DEFAULT_ACCOUNT : account;
    }

    public String getPassword() {
        return Strings.isNullOrEmpty(password) ? DEFAULT_PASSWORD : password;
    }

}
