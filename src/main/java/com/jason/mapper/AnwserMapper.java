package com.jason.mapper;

import com.jason.pojo.Anwser;

import java.util.List;
import java.util.Map;

public interface AnwserMapper {
    void insertAnwserList(List<Anwser> anwserAllList );

    List<Anwser> findAnwserListByCondition(Map map);

    void updateAnwserList(List<Anwser> anwserAllList);
}
