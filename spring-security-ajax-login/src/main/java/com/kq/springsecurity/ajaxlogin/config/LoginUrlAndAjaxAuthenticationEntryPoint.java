package com.kq.springsecurity.ajaxlogin.config;

import com.kq.springsecurity.ajaxlogin.dto.DtoResult;
import com.kq.springsecurity.util.JacksonUtil;
import com.kq.springsecurity.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 兼容LoginUrlAuthenticationEntryPoint和ajax
 * @author kq
 * @date 2021-04-23 9:44
 * @since 2020-0630
 */
public class LoginUrlAndAjaxAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {


    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public LoginUrlAndAjaxAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if(isAjaxRequest(request)){

            DtoResult result = new DtoResult();
            result.setCode(HttpServletResponse.SC_UNAUTHORIZED+"");
            if(authException!=null) {
                result.setResult(authException.getMessage());
            }

            ResponseUtil.writeJson(JacksonUtil.toJSon(result),response);

//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
        }else{
            super.commence(request,response,authException); //走LoginUrlAuthenticationEntryPoint逻辑
        }

    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

}
