package Mail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailSend {
    public void send(String host, int port, String username, String password,
                     String receiver, String title, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
//	        session.setDebug(true); //for debug

        Message mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress("project_boardcar@naver.com")); // id
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiver));			// 수신자
            mimeMessage.setSubject(title);							// 제목
            mimeMessage.setText(body);					// 내용
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}