package com.hello.boot.spring5boot.board;


import com.hello.boot.spring5boot.model.Board;
import com.hello.boot.spring5boot.model.Member;
import com.hello.boot.spring5boot.mybatis.BoardMapper;
import com.hello.boot.spring5boot.mybatis.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardMapperUnitTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("boardMapper select Test")
    void selectBoard(){
        List<Board> result = boardMapper.selectBoard();

        System.out.println(result);
        assertNotNull(result);
    }



}
