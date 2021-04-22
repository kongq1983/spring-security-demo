package com.kq.springboot.jwt.config;

import com.kq.springboot.jwt.entity.AjaxResult;
import com.kq.springboot.jwt.util.JacksonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kq
 * @date 2020-10-28 16:13
 * @since 2020-0630
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        AjaxResult<String> responseBody = new AjaxResult();

        if(e!=null){
            responseBody.setSuccess(false);
            responseBody.setResult(e.getMessage());
        }

        httpServletResponse.getWriter().write(JacksonUtil.toJSon(responseBody));
    }
}