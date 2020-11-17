package com.lxl.webtool.model;/**
 * Description:
 *
 * @author Administrator
 * @date 2020/11/13 10:36
 */

/**
 * 记录壁虎客户端信息
 * ClassName BHClientInfoRequest
 *
 * @Author Administrator
 * @Date 2020/11/13  10:36
 * Version 1.0
 **/
public class BHClientInfoRequest {


    /**
     * 渠道信息
     */
    private String channelInfoAll;

    /**
     * 车船税信息
     */
    private String channelInfoTaxCityCode;

    /**
     * 税务机构信息
     */
    private String channelInfoTaxOrgCode;

    /**
     * 备用信息1
     */
    private String info1;

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getChannelInfoAll() {
        return channelInfoAll;
    }

    public void setChannelInfoAll(String channelInfoAll) {
        this.channelInfoAll = channelInfoAll;
    }

    public String getChannelInfoTaxCityCode() {
        return channelInfoTaxCityCode;
    }

    public void setChannelInfoTaxCityCode(String channelInfoTaxCityCode) {
        this.channelInfoTaxCityCode = channelInfoTaxCityCode;
    }

    public String getChannelInfoTaxOrgCode() {
        return channelInfoTaxOrgCode;
    }

    public void setChannelInfoTaxOrgCode(String channelInfoTaxOrgCode) {
        this.channelInfoTaxOrgCode = channelInfoTaxOrgCode;
    }


}
