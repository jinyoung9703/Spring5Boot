package com.hello.boot.spring5boot.mybatis;


import com.hello.boot.spring5boot.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface GalleryMapper {
    int insertGallery(Gallery g);

    int insertGalAttach(GalAttach ga);

    List<Gallery> selectGallery(int stnum);

    Gallery selectOneGallery(String gno);

    int selectCountGallery();

    GalAttach selectOneGalleryAttach(String gno);

    int lastGalGno();

//    int insertPdsComment(PdsComment pc);
//
//    List<PdsComment> selectPdsComment(String pno);
//
//    int insertPdsReply(PdsComment pc);

    /*int updateViewPds(String bno);

    List<Pds> selectFindPds(Map<String, Object> params);

    int countFindPds(Map<String, Object> params);*/

}
