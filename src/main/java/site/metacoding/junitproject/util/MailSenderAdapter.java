package site.metacoding.junitproject.util;

import org.springframework.stereotype.Component;

@Component
public class MailSenderAdapter implements MailSender{
    private Mail mail;

    public MailSenderAdapter() {
        this.mail = new Mail();
    }

    @Override
    public boolean send() {
        //return true;
        return mail.sendMail();
    }
}
