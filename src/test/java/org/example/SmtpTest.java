package org.example;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public  class SmtpTest {
    private static void sendEmailWithReport() {
        String to = "mxl0310@163.com"; // 收件人邮箱
        String from = "1779558865@qq.com.com"; // 发件人邮箱
        String host = "smtp.qq.com"; // SMTP 服务器地址
        final String username = "1779558865@qq.com"; // 发件人邮箱用户名
        final String password = "maxiaolei"; // 发件人邮箱密码

        // 设置邮件服务器
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // 或其他端口


        // 获取默认的邮件会话
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Allure Test Report");

            // 创建邮件正文
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("测试报告已生成，请查看附件。");
            message.setText(emailBody.toString());

            // 添加附件
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailBody.toString());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // 添加 Allure 报告路径
            String allureReportPath = "path/to/allure-report.zip"; // 请更改为你的报告路径
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(allureReportPath));
            multipart.addBodyPart(attachmentPart);

            // 设置邮件内容
            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功。");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
