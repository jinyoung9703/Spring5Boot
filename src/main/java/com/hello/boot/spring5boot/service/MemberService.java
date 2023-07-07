package com.hello.boot.spring5boot.service;

import com.hello.boot.spring5boot.model.Member;

import java.util.List;

public interface MemberService {

    boolean saveMember(Member m);
    List<Member> readMember();
}
