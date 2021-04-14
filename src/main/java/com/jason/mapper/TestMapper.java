package com.jason.mapper;

import com.jason.pojo.Test;

import java.util.List;
import java.util.Map;

public interface TestMapper {

    List<Test> findTestByCondition(Map map);

    void insertTest(Test test);
    void updateTest(Test test);

}
