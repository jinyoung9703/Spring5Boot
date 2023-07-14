package com.hello.boot.spring5boot.dao;

import com.hello.boot.spring5boot.model.Pds;
import com.hello.boot.spring5boot.model.PdsAttach;

public interface PdsDAO {
    int insertPds(Pds p);

    int insertPdsAttach(PdsAttach pa);

}
