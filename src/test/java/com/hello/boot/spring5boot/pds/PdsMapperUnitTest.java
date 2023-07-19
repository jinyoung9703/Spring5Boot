package com.hello.boot.spring5boot.pds;


import com.hello.boot.spring5boot.model.Board;
import com.hello.boot.spring5boot.model.Pds;
import com.hello.boot.spring5boot.model.PdsAttach;
import com.hello.boot.spring5boot.model.PdsComment;
import com.hello.boot.spring5boot.mybatis.PdsMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PdsMapperUnitTest {

    @Autowired
    private PdsMapper pdsMapper;

    @Test
    @DisplayName("PdsMapper lastId Test")
    @Transactional
    void lastIdPds(){
        Pds p = new Pds();
        p.setUserid("abc123"); p.setTitle("테스트");
        p.setContents("테스트"); p.setIpaddr("127.0.0.1");

        pdsMapper.insertPds(p);
        int result = pdsMapper.lastPdsPno();

        assertNotNull(result);
    }

    @Test
    @DisplayName("PdsMapper selectOnePA Test")
    void selectOnePA(){
      String pno = "18";

      PdsAttach result = pdsMapper.selectOnePdsAttach(pno);

        assertNotNull(result);
    }

    @Test
    @DisplayName("PdsMapper selectPC Test")
    void selectPdsComments(){
        String pno = "18";

       List<PdsComment> result = pdsMapper.selectPdsComment(pno);

        assertNotNull(result);
    }

    @Test
    @DisplayName("PdsMapper newcomment Test")
//    @Transactional
    void newcomment(){
        PdsComment pc = new PdsComment();
        pc.setUserid("abc123"); pc.setComments("테스트");
        pc.setPno("14");


        int result = pdsMapper.insertPdsComment(pc);

        assertNotNull(result);
    }
}
