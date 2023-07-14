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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Test
    @DisplayName("BoardDAO insert Test")
    @Transactional
    void insertBoard(){
        Board b = new Board();
        b.setUserid("abc123"); b.setTitle("테스트");
        b.setContents("테스트"); b.setIpaddr("127.0.0.1");

        int result = bdao.insertBoard(b);

        assertEquals(result,1);
    }
    @Test
    @DisplayName("boardDAO countPage Test")
    void countPage() {
        int result = bdao.selectCountBoard();

        assertNotNull(result);
    }
    @Test
    @DisplayName("boardDAO findBoard Test")
    void findBoard(){
        Map<String, Object> params = new HashMap<>();
        params.put("findtype","titcont");
        params.put("findkey","의료");
        params.put("stnum",0);

        List<Board> result = bdao.selectFindBoard(params);


        assertNotNull(result);
    }

    @Test
    @DisplayName("boardDAO countfindBoard Test")
    void countfindBoard(){
        Map<String, Object> params = new HashMap<>();
        params.put("findtype","titcont");
        params.put("findkey","의료");

        int result = bdao.countFindBoard(params);


        assertNotNull(result);
    }



}
/* page 1 :0~24
*  page 2 :25~49
*  page 3 :50~74
*
*  page n :m~m+25
*  m = (n-1) * 25 */
