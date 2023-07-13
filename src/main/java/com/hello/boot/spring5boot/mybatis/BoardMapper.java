package com.hello.boot.spring5boot.mybatis;


import com.hello.boot.spring5boot.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(Board m);

    List<Board> selectBoard(int stnum);

    Board selectOneBoard(String bno);

    int updateViewBoard(String bno);

    int selectCountBoard();


}
