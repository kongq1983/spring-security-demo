package com.kq.springboot.jwt.entity;

/**
 * @author kq
 * @date 2020-10-28 16:15
 * @since 2020-0630
 */
public class AjaxResult<T> {

    private boolean success = true;
    private T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "success=" + success +
                ", result=" + result +
                '}';
    }
}
