package com.hsjy.manager.hsjymanager.dao;

import com.hsjy.manager.hsjymanager.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TestDao {
    List<Test> findall();
}
