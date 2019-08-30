package com.email.utils;

/**
 * Descr: 发送邮件的工具类
 *
 */
public class SendEmailUtil {

    /**
     * 发送邮件
     * @param receiveEmail  收件人邮箱地址
     * @param emailTitle    邮件标题
     * @param emailBody     邮件正文
     * @return
     */
    public static boolean sendEmail(String receiveEmail,String emailTitle,String emailBody){
        System.out.println(">>>开发发送邮件：\n"+receiveEmail+"\n"+emailTitle+"\n"+emailBody+"\n");

        try {
            Mail.send(receiveEmail, emailTitle, emailBody);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送邮件出错了！！");
            return false;
        }

        return true;
    }

    /**
     * 发送找回密码验证码的邮件
     * @param receiveEmail
     * @param validateCode
     * @return
     */
    public static boolean sendPasswordCaptchaEmail(String receiveEmail,String validateCode){
        return sendEmail(receiveEmail, "找回密码-验证码", "您正在进行找回密码操作，当前验证码为："+validateCode);
    }

}
