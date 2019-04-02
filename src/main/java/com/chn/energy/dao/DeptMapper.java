package com.chn.energy.dao;

import com.chn.energy.model.Dept;
import com.chn.energy.model.DeptExample;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    List<Dept> selectByExample(DeptExample example);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}