package com.f.handler;

import com.f.helper.WebHelper;
import com.f.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: bvsoo
 * Date: 2018/9/14
 * Time: 下午4:49
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        WebHelper.writeMessage(response, ResponseVo.builder().code(HttpStatus.FORBIDDEN).message("账号或密码错误！").build());
    }
}
