package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

    //파티 신청 시, 모집글 작성자에게 참여 요청에 대한 알림을 이메일로 전송
    //신청에 대한 내용을 html로 작성하고 전송함.
    //SimpleMailMessage는 html코드를 작성하기 어렵기 때문에 MimeMessageHelper를 통해 작성.
    public void sendHTMLMail(String toEmail, String name, String title) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String htmlMsg = "<!DOCTYPE html>" +
                "<html>" +
                "<head></head>" +
                "<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #F7F7F7;\">" +
                "<div style=\"width: 100%; max-width: 600px; margin: auto; background-color: #FFFFFF; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">" +
                "<div style=\"text-align: center; margin-bottom: 20px;\">" +
                "<h2 style=\"color: #333333; border-bottom: 2px solid #49965F; padding-bottom: 10px;\">*혼밥멈춰* 모집글 참가 요청 메일</h2>" +
                "</div>" +
                "<div style=\"margin-top: 30px; margin-bottom: 20px; color: #555555;\">" +
                "<h3 style=\" margin-bottom: 50px; text-align: center;\"><신청이 들어온 모집 글 : \""+title+"\"></h3>" +
                "<h4 style=\" margin-bottom: 10px;\">안녕하세요, "+name+"님!</h4>" +
                "<p style=\" margin-bottom: 5px;\">당신의 모집글에 참가하고자 하는 사람의 요청이 접수되었습니다.</p>" +
                "<p>참가자의 상세 정보를 확인하고 참여 여부를 결정해주세요!</p>" +
                "</div>" +
                "<div style=\"text-align: center; color: #888888; margin-top: 40px;\">" +
                "<h4>항상 저희 '혼밥멈춰'를 이용해주셔서 감사합니다 :)</h4>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        helper.setTo(toEmail);
        helper.setSubject("[* <혼밥멈춰!!> 파티 신청 요청이 들어왔습니다. *]");
        helper.setText(htmlMsg, true); // true indicates that this is HTML mail

        emailSender.send(message);
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