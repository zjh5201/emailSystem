package com.email.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 最终进行发送邮件的代码部分，如果对过程不了解，参考MailTest.java文件
 */
public class Mail {

    private static String myEmailAccount = "2312330466@qq.com";//修改为自己的qq邮箱账号
    private static String myEmailPassword = "lplicpsposvudife";//修改为自己的邮箱授权码
    private static String myEmailSMTPHost = "smtp.qq.com";//如果用qq邮箱不用修改，如果用163邮箱，修改为smtp.163.com
    private static String sendUser = "365邮箱官方团队";

    public static void send(String receive,String title,String body) throws Exception {
        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        final String smtpPort = "465";
      //开启SSL加密
  		MailSSLSocketFactory sf = new MailSSLSocketFactory();
  		sf.setTrustAllHosts(true);
  		props.put("mail.smtp.ssl.enable", "true");
  		props.put("mail.smtp.ssl.socketFactory", sf);
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true); 
        MimeMessage message = createMimeMessage(session, myEmailAccount, receive,title,body);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    /**
     * 创建邮件对象
     */
    private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String title,String body) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, sendUser, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(title, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(body, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}