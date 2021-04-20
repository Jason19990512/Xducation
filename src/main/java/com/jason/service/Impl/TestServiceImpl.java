package com.jason.service.Impl;

import com.jason.mapper.TestMapper;
import com.jason.pojo.Test;
import com.jason.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public Integer makeTestNo(String refcourseno, String memacc, String testscope) {
        Test test = new Test();
        test.setRefcourseno(Integer.parseInt(refcourseno));
        test.setRefmemacc(memacc);
        test.setTestscope(Integer.parseInt(testscope));
        testMapper.insertTest(test);

        return test.getTestno();
    }
}
