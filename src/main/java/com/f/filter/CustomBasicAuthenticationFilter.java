package com.f.filter;

import com.f.base.Auth;
import com.f.configure.properties.TokenConfigure;
import com.f.helper.TokenHelper;
import com.f.helper.WebHelper;
import com.f.mvc.entity.User;
import com.f.mvc.service.UserService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * User: bvsoo
 * Date: 2018/9/13
 * Time: 上午11:47
 */
@Log4j2
@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    private TokenConfigure tokenConfigure;
    @Autowired
    private UserService userService;


    public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(Auth.JWT_HEADER_KEY);
        if (Strings.isNullOrEmpty(token)) token = request.getParameter(Auth.JWT_HEADER_KEY);
        if (Strings.isNullOrEmpty(token) || !token.startsWith(Auth.JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Pair<HttpStatus, Claims> authenticationTokenPair = getAuthentication(token);
        if (!authenticationTokenPair.getLeft().equals(HttpStatus.OK)) {
            WebHelper.writeMessage(response, authenticationTokenPair.getLeft());
            return;
        }
        Claims claims = authenticationTokenPair.getRight();
        String account = claims.get(Auth.USER_ACCOUNT_KEY, String.class);
        User user = userService.findUserByAccount(account);
        if (user == null) {
            WebHelper.writeMessage(response, authenticationTokenPair.getLeft());
            return;
        }
        request.setAttribute(Auth.USER_ID_KEY, user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null, Lists.newArrayList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Pair<HttpStatus, Claims> getAuthentication(String token) {
        Claims claims = null;
        try {
            final String newToken = token.replace(Auth.JWT_TOKEN_PREFIX, "");
            claims = Jwts.parser()
                    .setSigningKey(TokenHelper.getSecret(tokenConfigure.getSecret()))
                    .parseClaimsJws(newToken).getBody();
            return Pair.of(HttpStatus.OK, claims);
        } catch (ExpiredJwtException e) {
            log.warn(e.getLocalizedMessage(), e);
            return Pair.of(HttpStatus.UNAUTHORIZED, claims);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.warn(e.getLocalizedMessage(), e);
            return Pair.of(HttpStatus.UNAUTHORIZED, claims);
        } catch (UnsupportedEncodingException e) {
            return Pair.of(HttpStatus.UNAUTHORIZED, null);
        }
    }

}
