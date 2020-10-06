package com.fly.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件测试类
 */
public class emailConfig {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Hello 李哥");
        simpleMailMessage.setText("这是一个邮件任务测试");
        simpleMailMessage.setTo("877236402@qq.com");
        simpleMailMessage.setFrom("1903203411@qq.com");
        mailSender.send(simpleMailMessage);
    }
    @Test
    void contextLoadsPlus() throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //正文
        helper.setSubject("Hello 李哥");
        helper.setText("<p style='color:red'>这是一个邮件任务测试的加强版</p>",true);
        //附件
        helper.addAttachment("2-校园招聘系统概要设计.docx",new File("F:\\项目文档\\2-校园招聘系统概要设计.docx"));
        helper.setTo("877236402@qq.com");
        helper.setFrom("1903203411@qq.com");
        mailSender.send(mimeMessage);
    }
}
