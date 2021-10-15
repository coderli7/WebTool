package com.lxl.webtool.controller;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName TestController
 *
 * @Author bruce
 * @Date 2021/4/21  16:22
 * Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {


    //@Autowired
    //private RabbitTemplate rabbitTemplate;
    //
    //@RequestMapping("rabbitmq1")
    //public void test() {
    //    try {
    //        HashMap<String, String> map = new HashMap<String, String>();
    //        map.put("id", "2");
    //        map.put("name", "cat");
    //        //根据key发送到对应的队列
    //        rabbitTemplate.convertAndSend("que_cat_key", map);
    //    } catch (AmqpException e) {
    //        e.printStackTrace();
    //    }
    //}
}
