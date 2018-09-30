package com.f.mvc.action;

import com.f.base.Auth;
import com.f.base.BaseAction;
import com.f.configure.properties.TokenConfigure;
import com.f.helper.TokenHelper;
import com.f.mvc.entity.User;
import com.f.mvc.service.UserService;
import com.f.vo.ResponseVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * User: bvsoo
 * Date: 2018/9/15
 * Time: 下午1:43
 */
@RestController
@RequestMapping(value = "/token")
@Api(value = "/token", description = "令牌")
public class TokenAction extends BaseAction {
    private static final long serialVersionUID = -1445998086128555384L;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenConfigure tokenConfigure;

    /**
     * 刷新令牌
     *
     * @return
     */
    @ApiOperation(value = "/refresh", notes = "刷新令牌")
    @RequestMapping(value = "/refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo refresh(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Long centerUserId = super.getUserId(request);
        User user = userService.findUserById(centerUserId);
        String token = Auth.JWT_TOKEN_PREFIX + Jwts.builder()
                .claim(Auth.USER_ID_KEY, user.getId()).claim(Auth.USER_ACCOUNT_KEY, user.getAccount())
                .setSubject(user.getAccount())
                .setExpiration(TokenHelper.expirationDate(tokenConfigure.getTimeout()))
                .signWith(SignatureAlgorithm.forName(tokenConfigure.getAlgorithm()), TokenHelper.getSecret(tokenConfigure.getSecret()))
                .compact();
        response.setHeader(Auth.JWT_HEADER_KEY, token);
        return ResponseVo.builder().build();
    }
}
