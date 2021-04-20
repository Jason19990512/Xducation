package com.jason.mapper;

import com.jason.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnwserMapper {
    void insertAnwserList(List<Answer> anwserAllList );

    List<Answer> findAnwserListByCondition(Map map);

    void updateAnwserList(List<Answer> anwserAllList);
}
