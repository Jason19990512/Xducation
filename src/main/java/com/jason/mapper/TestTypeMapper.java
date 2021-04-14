package com.jason.mapper;

import com.jason.pojo.TestType;

import java.util.List;

public interface TestTypeMapper {
    List<TestType> findAllTestType();

    void insertTestType(TestType testType);

    void updateTestType(TestType testType);
}
