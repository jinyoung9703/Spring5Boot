package com.hello.boot.spring5boot;


import com.hello.boot.spring5boot.dao.MemberDAO;
import com.hello.boot.spring5boot.dao.MemberDAOImpl;
import com.hello.boot.spring5boot.model.Member;
import com.hello.boot.spring5boot.mybatis.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberMapperUnitTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("MemberMapper insert Test")
    void insertMember(){
        Member m = new Member(null,"","","",
                "","","","","","",null);

        int result = memberMapper.insertMember(m);
        System.out.println(result);
        assertEquals(result,1);
    }
    @Test
    @DisplayName("MemberMapper select Test")
    void selectMember(){
        List<Member> result = memberMapper.selectMember();


        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("MemberMapper selectOneMember Test")
    void selectOneMember(){
        Member m = new Member();
        m.setUserid("abc123");
        m.setPasswd("987xyz");

        Member result = memberMapper.selectOneMember(m);


        System.out.println(result);
        assertNotNull(result);
    }


}
