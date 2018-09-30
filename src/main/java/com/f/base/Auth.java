package com.f.base;

/**
 * User: bvsoo
 * Date: 2018/9/13
 * Time: 上午11:49
 */
public interface Auth {
    /**
     * request头中JWT键名
     */
    String JWT_HEADER_KEY = "authorization";
    /**
     * JWT前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer;";

    /**
     * 用户标识
     */
    String USER_ID_KEY = "uid";

    /**
     * 账号标识
     */
    String USER_ACCOUNT_KEY = "account";
}
