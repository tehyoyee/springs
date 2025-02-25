package com.taehyeong.security_idpw.member.service;

import com.taehyeong.security_idpw.member.entity.Member;
import com.taehyeong.security_idpw.member.entity.Role;
import com.taehyeong.security_idpw.member.entity.dto.MemberCreateReqDTO;
import com.taehyeong.security_idpw.member.repository.MemberRepository;
import com.taehyeong.security_idpw.member.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void createMember(MemberCreateReqDTO reqDTO) throws Exception {

        Role initialRole = roleRepository.findByName("ROLE_GENERAL").orElseThrow(
                () -> new Exception("Role not exist")
        );

        memberRepository.save(
                Member.builder().
                        username(reqDTO.username()).
                        password(passwordEncoder.encode(reqDTO.password())).
                        role(initialRole)
                        .build()
        );


    }

    public void loadMemberDetails() throws Exception {

        Member member = memberRepository.findById(1L).orElseThrow(
                () -> new Exception("")
        );
        System.out.println(member.toString());



    }

}
