package yougboyclub.honbabstop.config;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {


    @Value("587")
    private int port; // SMTP 포트

    @Value("true")
    private boolean auth; // 인증 여부

    @Value("true")
    private boolean starttls; // STARTTLS 사용 여부

    @Value("true")
    private boolean startlls_required; // STARTTLS 필수 여부

    @Value("thkim610@gmail.com")
    private String id; // 관리자 이메일 계정 아이디

    @Value("klhffwdwthcvelzz")
    private String password; // 관리자 이메일 계정 비밀번호

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com"); // SMTP 호스트
        javaMailSender.setUsername(id); // 이메일 계정 아이디
        javaMailSender.setPassword(password); // 이메일 계정 비밀번호
        javaMailSender.setPort(port); // SMTP 포트
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8"); // 기본 인코딩 설정
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", auth); // 인증 여부
        properties.put("mail.smtp.starttls.enable", starttls); // STARTTLS 사용 여부
        properties.put("mail.smtp.starttls.required", startlls_required); // STARTTLS 필수 여부
        return properties;
    }
}

