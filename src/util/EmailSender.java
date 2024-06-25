package util;

import javax.mail.*;
import javax.mail.internet.*;
import javafx.concurrent.Task;
import java.util.Properties;

public class EmailSender {
	public static Task<Void> sendReminderEmail(String toEmail, String subject, String body) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                Properties properties = EmailConfig.getEmailProperties();
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EmailConfig.getUsername(), EmailConfig.getPassword());
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(EmailConfig.getUsername()));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                    message.setSubject(subject);
                    message.setText(body);

                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
