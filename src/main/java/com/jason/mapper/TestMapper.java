package com.jason.mapper;

import com.jason.pojo.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    List<Test> findTestByCondition(Map map);

    Integer insertTest(Test test);
    void updateTest(Test test);

}
