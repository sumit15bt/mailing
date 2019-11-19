package mailing;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailing {

    public static void main(String[] args) {
        // TODO code application logic here
        String OFFICIAL_EMAIL_ID = "";
        String EMAIL_PASSWORD = "";
        String EMAIL_HOST = "smtp.gmail.com";
        String EMAIL_PORT = "587";
        String SSL = "false";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EMAIL_HOST);
        props.put("mail.smtp.user", OFFICIAL_EMAIL_ID);
        props.put("mail.smtp.password", EMAIL_PASSWORD);
        props.put("mail.smtp.ssl.trust", EMAIL_HOST);
        props.put("mail.smtp.port", EMAIL_PORT);
        props.put("mail.smtp.ssl.enable", SSL);
        props.put("mail.smtp.auth", true);
        Session session;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(OFFICIAL_EMAIL_ID, EMAIL_PASSWORD);
            }

        });

        try {
            System.out.println("mail thread. ...............................");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(OFFICIAL_EMAIL_ID));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("saransh@contaque.com"));
            message.setSubject("test mailing");
            String msg = "checking mailing code";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
             System.out.println("mail done!!!!!!!!. ...............................");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

}
