package com.kq.springsecurity.ajaxlogin.controller;

import com.kq.springsecurity.ajaxlogin.dto.DtoResult;
import com.kq.springsecurity.ajaxlogin.dto.LoginVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 * @author kq
 * @date 2021-04-23 9:00
 * @since 2020-0630
 */

@RestController
@RequestMapping()
public class LoginController {


    @PostMapping("/ajax/login")
    public DtoResult login(LoginVo loginVo){

        System.out.println("loginVo="+loginVo);

        DtoResult result = new DtoResult();

        return result;
    }


}
