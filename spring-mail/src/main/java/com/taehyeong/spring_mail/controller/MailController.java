package com.taehyeong.spring_mail.controller;

import com.taehyeong.spring_mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail")
    public String sendTestMail() {

        System.out.println("asdf");
//        mailService.sendMail(
//                "25perius@gmail.com",
//                "메일 제목입니다",
//                "안녕하세요, 스프링 메일 전송 테스트입니다."
//        );

        return "메일 발송 완료!";
    }
}
