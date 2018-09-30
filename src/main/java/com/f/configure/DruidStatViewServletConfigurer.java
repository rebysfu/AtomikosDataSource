package com.f.configure;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebServlet;

/**
 * User: bvsoo
 * Date: 2018/7/2
 * Time: 下午2:06
 */
@WebServlet(urlPatterns = "/druid/*")
public class DruidStatViewServletConfigurer extends StatViewServlet {
    private static final long serialVersionUID = -8552945916605169398L;
}
