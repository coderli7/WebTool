package com.lxl.webtool.schduletask;

import com.lxl.webtool.dao.ImageCaseInfoService;
import com.lxl.webtool.dao.MailService;
import com.lxl.webtool.dao.pojo.TbImageCaseInfo;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SendMailTask {

    private static final Mylogger logger = MyLoggerFactory
            .getLogger(SendMailTask.class);

    @Autowired
    private ImageCaseInfoService imageCaseInfoService;

    @Autowired
    private MailService mailService;

    /**
     * 1.查询当前未完成,切超时时间大于1个小时的数据
     */
    @Scheduled(cron = "0 0/10 6-23 * * ? ")
    public void sendResultToTx() {

        // 1.查询
        List<TbImageCaseInfo> timeOutCaseList = imageCaseInfoService
                .findTimeOutCase(30);

        // 2.生成超时数据
        if (timeOutCaseList != null && timeOutCaseList.size() > 0) {
            StringBuilder mailSb = new StringBuilder();
            for (TbImageCaseInfo tbImageCaseInfo : timeOutCaseList) {
                String curCaseInfo = String.format(
                        "案件号:[%s]      案件日期：[%s]\r\n",
                        tbImageCaseInfo.getCaseid(),
                        tbImageCaseInfo.getCasedate());
                mailSb.append(curCaseInfo);
            }

            // 发送邮件提醒
            if (StringUtils.isNotBlank(mailSb.toString())) {
                mailService.sendSimpleMail(null, "请注意!!!票据存在超时未识别任务哦!",
                        mailSb.toString());
            }
        }
    }
}
