package com.lxl.webtool.model.WebApiResponse;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import java.io.Serializable;

/**
 * ClassName BHChannelInfoDataResponse
 *
 * @Author bruce
 * @Date 2021/1/14  10:33
 * Version 1.0
 **/
public class BHChannelInfoDataResponse implements Serializable {

    private String cookie;

    private String proxyUrl;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }
}
