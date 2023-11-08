package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;

    //이메일을 발송하는 메서드 파라미터로 이메일 주소, 이메일 제목, 이메일 내용을 입력 받음.
    public void sendEmail(String toEmail, String title, String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    //발신할 이메일 데이터 세팅
    //발송할 이메일 데이터를 설정하는 메서드.
    //수신자 이메일 주소, 이메일 제목, 이메일 내용을 입력 받아 SimpleMailMessage 객체를 생성하여 반환
    private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail); //수신자 이메일 주소
        message.setSubject(title); //이메일 제목
        message.setText(text); //이메일 내용

        return message;
    }
}