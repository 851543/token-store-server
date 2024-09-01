package com.token.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 编码 1-成功 0-和其它数字为失败
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 成功响应
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    /**
     * 成功响应 携带数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T object){
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    /**
     * 失败响应
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<T>();
        result.msg = msg;
        result.code = 0;
        return result;
    }
}
