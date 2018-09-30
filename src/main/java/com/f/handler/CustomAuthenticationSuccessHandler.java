package com.f.handler;

import com.f.base.Auth;
import com.f.configure.properties.TokenConfigure;
import com.f.helper.TokenHelper;
import com.f.helper.WebHelper;
import com.f.mvc.entity.User;
import com.f.mvc.service.UserService;
import com.f.vo.ResponseVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: bvsoo
 * Date: 2018/9/13
 * Time: 下午2:26
 */
@Log4j2
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private TokenConfigure tokenConfigure;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByAccount(userDetails.getUsername());
        String token = Auth.JWT_TOKEN_PREFIX + Jwts.builder()
                .claim(Auth.USER_ID_KEY, user.getId()).claim(Auth.USER_ACCOUNT_KEY, user.getAccount())
                .setSubject(user.getAccount())
                .setExpiration(TokenHelper.expirationDate(tokenConfigure.getTimeout()))
                .signWith(SignatureAlgorithm.forName(tokenConfigure.getAlgorithm()), TokenHelper.getSecret(tokenConfigure.getSecret()))
                .compact();
        response.setHeader(Auth.JWT_HEADER_KEY, token);
        WebHelper.writeMessage(response, ResponseVo.builder().data(user).build());
    }
}
