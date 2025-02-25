package com.taehyeong.design_patterns;

import com.taehyeong.design_patterns.event.Member;
import com.taehyeong.design_patterns.event.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DesignPatternsApplication {
	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);
	}

}
