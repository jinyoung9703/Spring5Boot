package com.hello.boot.spring5boot.mybatis;


import com.hello.boot.spring5boot.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int insertBoard(Board m);

    List<Board> selectBoard(int stnum);

    Board selectOneBoard(String bno);

    int updateViewBoard(String bno);

    int selectCountBoard();

    List<Board> selectFindBoard(Map<String, Object> params);
    int countFindBoard(Map<String, Object> params);

}
