package com.taehyeong.design_patterns.event;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class MemberRepository {

    private final List<Member> memberList = new ArrayList<>();

    public MemberRepository() {
        this.memberList.add(Member.builder().name("asdf").build());
        this.memberList.add(Member.builder().name("qwer").build());
    }

}
