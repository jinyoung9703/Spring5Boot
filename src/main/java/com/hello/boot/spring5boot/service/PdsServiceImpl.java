package com.hello.boot.spring5boot.service;

import com.hello.boot.spring5boot.dao.PdsDAO;
import com.hello.boot.spring5boot.model.Pds;
import com.hello.boot.spring5boot.model.PdsAttach;
import com.hello.boot.spring5boot.model.PdsComment;
import com.hello.boot.spring5boot.utils.PdsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("psrv")
@RequiredArgsConstructor
public class PdsServiceImpl implements PdsService{

    final PdsDAO pdao;
    final PdsUtils pdsUtils;


    @Override
    public int newPds(Pds p) {

        return pdao.insertPds(p);
    }

    @Override
    public boolean newPdsAttach(MultipartFile attach, int pno) {

        // 첨부한 파일을 지정한 위치에 저장후 파일정보 리턴
        PdsAttach pa = pdsUtils.processUpload(attach); /* 압축해서 보냄*/
        pa.setPno(pno + ""); /* 형변환 없이 이래도 문자로 쓸 수 있음*/

        // 첨부파일 정보를 디비에 저장
        int pacnt = pdao.insertPdsAttach(pa);

        return (pacnt > 0) ? true : false; /* 참 거짓 선별 후 넘김 */
    }

    @Override
    public List<Pds> readpds(Integer cpg) {
        int stnum = (cpg -1) * 25;

        return pdao.selectPds(stnum);
    }

    @Override
    public int countPds() {

        return pdao.selectCountPds();
    }

    @Override
    public Pds readOnePds(String pno) {

        return pdao.selectOnePds(pno);
    }

    @Override
    public String readOnePdsAttach(String pno) {

        PdsAttach pa = pdao.selectOnePdsAttach(pno);

        return pa.getFname();
    }

    @Override
    public Map<String, Object> getHeaderResource(String fname) {
        Map<String, Object> objs = new HashMap<>();

        // 다운로드할 파일의 헤더(정보) 가져옴
        objs.put("header",pdsUtils.getHeader(fname));
        // 다운로드할 파일의 본체body 가져옴
        objs.put("resource",pdsUtils.getResource(fname));

        return objs;
    }

    @Override
    public boolean newPdsComment(PdsComment pc) {

        return (pdao.insertPdsComment(pc) > 0) ? true : false;
    }

    @Override
    public List<PdsComment> readPdsComment(String pno) {
        return pdao.selectPdsComment(pno);
    }

    @Override
    public boolean newPdsReply(PdsComment pc) {
        return (pdao.insertPdsReply(pc) > 0) ? true : false;
    }
}