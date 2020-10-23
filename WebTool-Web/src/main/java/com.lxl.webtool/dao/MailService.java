package com.lxl.webtool.dao;

public interface MailService {

	public void sendSimpleMail(String to, String subject, String content);
}
