package com.kq.springsecurity.ajaxlogin.dto;

/**
 * @author kq
 * @date 2021-04-23 9:06
 * @since 2020-0630
 */
public class DtoResult {

    private String code = "0";
    private Object result;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DtoResult{" +
                "code='" + code + '\'' +
                ", result=" + result +
                '}';
    }
}
