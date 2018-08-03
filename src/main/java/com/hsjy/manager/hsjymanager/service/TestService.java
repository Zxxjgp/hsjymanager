package com.hsjy.manager.hsjymanager.service;

import com.hsjy.manager.hsjymanager.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface TestService {
    List<Test> findall();
}
