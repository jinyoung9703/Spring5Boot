package com.hello.boot.spring5boot.board;


import com.hello.boot.spring5boot.dao.BoardDAO;
import com.hello.boot.spring5boot.dao.BoardDAOImpl;
import com.hello.boot.spring5boot.model.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BoardDAOImpl.class)
public class BoardDAOUnitTest {

    @Autowired
    private BoardDAO bdao;

    @Test
    @DisplayName("BoardDAO select Test")
    void selectBoard(){
        int cpg = 1;
        int stnum = (cpg - 1) *25;
        List<Board> results = bdao.selectBoard(stnum);
        //System.out.println(results);

        assertNotNull(results);

    }
    @Test
    @DisplayName("BoardDAO selectOneBoar Test")
    void selectOneBoard(){
       String bno = "1269";

        Board result = bdao.selectOneBoard(bno);

        assertNotNull(result);

    }



}
/* page 1 :0~24
*  page 2 :25~49
*  page 3 :50~74
*
*  page n :m~m+25
*  m = (n-1) * 25 */
