package com.kq.springsecurity.ajaxlogin.config;

import com.kq.springsecurity.ajaxlogin.dto.DtoResult;
import com.kq.springsecurity.util.JacksonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kq
 * @date 2021-04-23 10:25
 * @since 2020-0630
 */

//@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        DtoResult result = new DtoResult();

        result.setCode(HttpServletResponse.SC_UNAUTHORIZED+"");

        if(authException!=null) {
            result.setResult(authException.getMessage());
        }

        response.getWriter().write(JacksonUtil.toJSon(result));

    }
}
