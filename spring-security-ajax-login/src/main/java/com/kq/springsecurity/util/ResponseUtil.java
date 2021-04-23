package com.kq.springsecurity.util;

import javax.servlet.http.HttpServletResponse;

/**
 * @author kq
 * @date 2021-04-23 14:48
 * @since 2020-0630
 */
public class ResponseUtil {

    public static void writeJson(String content, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("applicaton/json;charset=UTF-8");
        try{
            response.getWriter().write(content);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
