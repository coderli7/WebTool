package com.lxl.webtool.dao;

import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

	private static final Mylogger logger = MyLoggerFactory
			.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.fromAddress}")
	private String fromAddress;

	@Value("${mail.toAddress}")
	private String toAddress;

	// MailMessage mailMessage;

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddress);
		String[] toMailArray = null;
		if (StringUtils.isNotBlank(to)) {
			// 如果传入，则以传入为准
			toMailArray = new String[]{to};
		} else {
			// 如果为未传入，则以配置文件中为准
			toMailArray = toAddress.split(",");
		}
		message.setTo(toMailArray);
		message.setSubject(subject);
		message.setText(content);

		try {
			// mailSender.send(message);
			// mailSender.send(message);
			logger.logMsg("简单邮件已经发送。");
		} catch (Exception e) {
			logger.logMsg("发送简单邮件时发生异常！" + e.getMessage());
		}
	}

}
