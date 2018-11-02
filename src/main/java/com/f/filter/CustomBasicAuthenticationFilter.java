package com.f.filter;

import com.f.base.Auth;
import com.f.configure.properties.TokenConfigure;
import com.f.helper.TokenHelper;
import com.f.helper.WebHelper;
import com.f.mvc.entity.auth.User;
import com.f.mvc.service.auth.UserService;
import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenConfigure tokenConfigure;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;


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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getAccount());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            logger.info("authenticated user " + user.getAccount() + ", setting security context");
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
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
