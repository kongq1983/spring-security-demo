package com.kq.springsecurity.ajaxlogin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kq
 * @date 2021-04-23 10:41
 * @since 2020-0630
 */
public class AjaxJsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) { // 必须以post方式提交
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = "";
        String password = "";
        try {
//            if(request.getInputStream().markSupported()) {
//                request.getInputStream().mark(Integer.MAX_VALUE);
//            }
//            String json = InputStreamUtil.readInputStream(request.getInputStream());
//            if(request.getInputStream().markSupported()) {
//                request.getInputStream().reset();
//            }
            username = request.getParameter("username");
            password = request.getParameter("password");
        } catch (Exception e) {
            logger.error("读取登陆数据失败", e);
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}