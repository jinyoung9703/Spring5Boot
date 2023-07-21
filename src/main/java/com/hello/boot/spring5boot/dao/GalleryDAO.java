package com.hello.boot.spring5boot.dao;

import com.hello.boot.spring5boot.model.GalAttach;
import com.hello.boot.spring5boot.model.Gallery;

import java.util.List;

public interface GalleryDAO {
    List<Gallery> selectGallery(int stnum);

    int insertGallery(Gallery g);

    int insertGalAttach(GalAttach ga);

    Gallery selectOneGallery(String gno);
}
