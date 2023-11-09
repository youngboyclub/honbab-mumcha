package yougboyclub.honbabstop.config;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    /**
     * application.properties 파일에서 이메일 인증을 위한 세팅 값을 가져와 필드에 주입
     */
    @Value("587")
    private int port; // SMTP 포트

    @Value("true")
    private boolean auth; // 인증 여부

    @Value("true")
    private boolean starttlsEnable; // STARTTLS 사용 여부

    @Value("true")
    private boolean starttlsRequired; // STARTTLS 필수 여부

    @Value("thkim610@gmail.com")
    private String id; // 관리자 이메일 계정 아이디

    @Value("klhffwdwthcvelzz")
    private String password; // 관리자 이메일 계정 비밀번호

    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout; //클라이언트가 SMTP 서버와의 연결을 설정하는 데 대기해야 하는 시간

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout; //클라이언트가 SMTP 서버로부터 응답을 대기해야 하는 시간

    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private int writeTimeout; //클라이언트가 작업을 완료하는데 대기해야 하는 시간

    @Bean //JavaMailSender 빈을 생성하는 메서드
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

    //JavaMailSender의 속성을 설정하기 위한 Properties 객체를 반환하는 메서드
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", auth); // 인증 여부
        properties.put("mail.smtp.starttls.enable", starttlsEnable); // STARTTLS 사용 여부
        properties.put("mail.smtp.starttls.required", starttlsRequired); // STARTTLS 필수 여부
        properties.put("mail.smtp.connectiontimeout", connectionTimeout);
        properties.put("mail.smtp.timeout", timeout);
        properties.put("mail.smtp.writetimeout", writeTimeout);

        return properties;
    }
}

