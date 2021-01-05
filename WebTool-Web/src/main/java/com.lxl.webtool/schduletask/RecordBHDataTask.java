package com.lxl.webtool.schduletask;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import org.springframework.scheduling.annotation.Scheduled;

/**
 * 记录壁虎成功率&失败率
 * ClassName RecordBHDataTask
 *
 * @Author bruce
 * @Date 2020/12/29  18:03
 * Version 1.0
 **/
public class RecordBHDataTask {


    @Scheduled(cron = "0 0/1 6-23 * * ? ")
    public void loginStatus() {

        /**
         * 1.定时检测登录状态
         *
         *
         */
        // String loginRetVal = MyHttpUtils.Post("http://cqa.91bihu.com/Login/CreateCaptcha", "");


    }
}
