package com.zhimin115200.test.SkyFavor.service.worker;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class MailWorker extends Worker{

    //add your mail server config
    static String ACCOUNT = "";
    static String PWD = "";
    static String SENDADDRESS = "";

    final static String pop3Server = "";
    final static String smtpServer = "";

    static Session SESSION;
    static Transport TRANSPORT;
    static{
        try{
            SESSION = initParams();
            TRANSPORT = connectMailSendServer(SESSION, ACCOUNT, PWD);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    String email;
    String subject;
    String content;
    public MailWorker( String email, String subject,String content){
        this.email = email;
        this.subject = subject;
        this.content = content;
    }
    @Override
    public void run() {
        super.run();
        sendMail();
    }
    static Session initParams() throws GeneralSecurityException {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.ssl.enable", "true");
        props.setProperty("mail.pop3.host", pop3Server);
        props.setProperty("mail.host", smtpServer);
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.pop3.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);
        return session;
    }

    static Transport connectMailSendServer(Session session, String user, String pwd) throws GeneralSecurityException, MessagingException {

        Transport transport = session.getTransport();
        transport.connect(user, pwd);
        return transport;
    }
    public static MimeMessage createMimeMessage( String receiveMail, String subject, String content) throws Exception {
        MimeMessage message = new MimeMessage(SESSION);
        message.setFrom(new InternetAddress(SENDADDRESS, "", "UTF-8"));
        message.setSubject(subject, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    void sendMail(){
        try {
            MimeMessage mimeMessage = createMimeMessage(email,subject,content);
            TRANSPORT.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
