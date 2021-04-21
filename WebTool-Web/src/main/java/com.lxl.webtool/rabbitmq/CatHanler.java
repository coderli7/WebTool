package com.lxl.webtool.rabbitmq;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * ClassName CatHanler
 *
 * @Author bruce
 * @Date 2021/4/21  16:10
 * Version 1.0
 **/
public class CatHanler implements MessageListener {


    @Override
    public void onMessage(Message message) {
        byte[] body = message.getBody();
        String str1 = new String(body);
        System.out.println(str1);
    }
}
