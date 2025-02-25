package com.taehyeong.security_idpw.member.controller;

import com.taehyeong.security_idpw.member.entity.dto.MemberCreateReqDTO;
import com.taehyeong.security_idpw.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void create(@RequestBody MemberCreateReqDTO reqDTO) throws Exception {

        memberService.createMember(reqDTO);

    }

    @GetMapping
    public void loadMemberDatails() throws Exception {

        memberService.loadMemberDetails();

    }

}
