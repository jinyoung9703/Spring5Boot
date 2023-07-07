package com.hello.boot.spring5boot.dao;

import com.hello.boot.spring5boot.model.Member;

import java.util.List;

public interface MemberDAO {

    int insertMember(Member m);
    List<Member> selectMember();
}
