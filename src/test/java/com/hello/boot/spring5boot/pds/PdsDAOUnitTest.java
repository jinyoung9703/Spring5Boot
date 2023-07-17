package com.hello.boot.spring5boot.pds;


import com.hello.boot.spring5boot.dao.BoardDAO;
import com.hello.boot.spring5boot.dao.BoardDAOImpl;
import com.hello.boot.spring5boot.dao.PdsDAO;
import com.hello.boot.spring5boot.dao.PdsDAOImpl;
import com.hello.boot.spring5boot.model.Board;
import com.hello.boot.spring5boot.model.Pds;
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
@Import(PdsDAOImpl.class)
public class PdsDAOUnitTest {

    @Autowired
    private PdsDAO pdao;

    @Test
    @DisplayName("PdsDAO select Test")
    void selectBoard(){
        int cpg = 1;
        int stnum = (cpg - 1) *25;
        List<Pds> results = pdao.selectPds(stnum);
        //System.out.println(results);

        assertNotNull(results);

    }

        @Test
        @DisplayName("PdsDAO selectOnePds Test")
        void selectOnePds(){
        String pno = "12";

        Pds result = pdao.selectOnePds(pno);

        assertNotNull(result);

    }


}
/* page 1 :0~24
*  page 2 :25~49
*  page 3 :50~74
*
*  page n :m~m+25
*  m = (n-1) * 25 */
