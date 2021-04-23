package com.kq.springsecurity.ajaxlogin.dto;

/**
 * @author kq
 * @date 2021-04-23 9:08
 * @since 2020-0630
 */
public class LoginVo {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
