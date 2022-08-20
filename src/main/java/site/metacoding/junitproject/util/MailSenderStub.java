package site.metacoding.junitproject.util;

import org.springframework.stereotype.Component;

// 가짜
@Component  // Ioc 컨테이너에 등록
public class MailSenderStub implements MailSender{
    @Override
    public boolean send() {
        return true;
    }
}
