package com.taehyeong.spring_mail.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    /**
     * 간단한 텍스트 메일을 보낼 때 사용 가능
     */
    public void sendMail(String to, String subject, String text) {

        // 메일 발송
        try {
            String htmlContent = "<html>" +
                    "<body>" +
                    "<h1 style='color: orange;'>인증번호 발송</h1>" +
                    "<p>인증번호는 <b>" + "1234" + "</b> 입니다.</p>" +
                    "</body>" +
                    "</html>";

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            // SimpleMailMessage 객체 생성
            SimpleMailMessage message1 = new SimpleMailMessage();
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            System.out.println("메일 보내는중");
            javaMailSender.send(message);
            System.out.println("메일 발송 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        javaMailSender.send();
    }
}