package com.hsjy.manager.hsjymanager.service.impl;

import com.hsjy.manager.hsjymanager.dao.TestDao;
import com.hsjy.manager.hsjymanager.entity.Test;
import com.hsjy.manager.hsjymanager.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public List<Test> findall() {
        return testDao.findall();
    }
}
