package com.lxl.webtool.enums;
/**
 * Description:
 *
 * @author Administrator
 * @date 2020/10/20 14:12
 */

/**
 * ClassName ResultEnum
 *
 * @Author Administrator
 * @Date 2020/10/20  14:12
 * Version 1.0
 **/
public enum ResultEnum {

    Success(0, "成功"), Falied(-1001, "");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
