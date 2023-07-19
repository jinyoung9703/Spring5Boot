package com.hello.boot.spring5boot.service;

import com.hello.boot.spring5boot.model.Pds;
import com.hello.boot.spring5boot.model.PdsComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PdsService {

    int newPds(Pds p);
    boolean newPdsAttach(MultipartFile attach, int pno);

    List<Pds> readpds(Integer cpg);

    int countPds();

    Pds readOnePds(String pno);

    String readOnePdsAttach(String pno);

    Map<String, Object> getHeaderResource(String fname);

    boolean newPdsComment(PdsComment pc);

    List<PdsComment> readPdsComment(String pno);

    boolean newPdsReply(PdsComment pc);
}
