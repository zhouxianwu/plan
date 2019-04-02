package com.chn.energy.dao;

import com.chn.energy.model.Plan;

import java.util.List;
import java.util.Map;

public interface PlanMapper {

    int delete(Integer id);

    int insert(Plan record);

    Plan selectById(Integer id);

    Plan selectByName(String userName);

    int update(Plan record);

    List<Plan> queryList(Map params);

    int count(Map params);
}