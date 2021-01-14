package com.lxl.webtool.model;/**
 * Description:
 *
 * @author Administrator
 * @date 2020/11/6 16:20
 */

import java.io.Serializable;

/**
 * 登录信息请求model
 * 可用作新增和查询入参
 * ClassName BhLoginInfoRequest
 *
 * @Author Administrator
 * @Date 2020/11/6  16:20
 * Version 1.0
 **/
public class BhLoginInfoRequest implements Serializable {

    private String channelKey;
    private String proxyUrl;

    private String cookie;
    private Object data;

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
