package com.kq.springsecurity.ajaxlogin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kq
 * @date 2021-04-23 10:52
 * @since 2020-0630
 */
public class MyAuthenticationManager implements AuthenticationManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    private GmAccountService accountService;
//
//    public GmAuthenticationManager(GmAccountService accountService) {
//        this.accountService = accountService;
//    }

    private static Map<String,String> userMap = new ConcurrentHashMap<>();

    static {
        userMap.put("root","123456");
        userMap.put("admin","123456");
        userMap.put("user1","123123");
        userMap.put("user2","123123");
        userMap.put("aaa","123123");
        userMap.put("bbb","123123");
        userMap.put("ccc","123123");
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(authentication.getPrincipal() == null || authentication.getCredentials() == null) {
            throw new BadCredentialsException("登陆验证失败,用户名或密码为空");
        }

        String username = (String)authentication.getPrincipal();

//        GmAccount gmAccount = accountService.getGmAccount(username);

        if(!userMap.containsKey(username)) {
            throw new BadCredentialsException("账户不存在，username:" + username);
        }

        String dbPassowrd = userMap.get(username);

        String password = (String)authentication.getCredentials();
        if(!dbPassowrd.equals(password)) {
            throw new BadCredentialsException("用户或密码错误");
        }
        logger.info("{} 登陆成功",username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();//这里添加权限，先略过，后面添加。

        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities);
    }
}