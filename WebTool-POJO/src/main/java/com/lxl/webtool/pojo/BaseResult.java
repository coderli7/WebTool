package com.lxl.webtool.pojo;/**
 * Description:
 *
 * @author Administrator
 * @date 2020/11/6 14:05
 */

import java.io.Serializable;

/**
 * ClassName BaseResult
 * 共用Resultmodel
 *
 * @Author Administrator
 * @Date 2020/11/6  14:05
 * Version 1.0
 **/
public class BaseResult implements Serializable {


    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
