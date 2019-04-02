package com.chn.energy.service;

import com.chn.energy.model.Plan;
import com.chn.energy.util.Page;

import java.util.Map;

/**
 * Created by zhouxianwu on 2019/3/27.
 */
public interface PlanService {

    int delete(Integer id);

    int insert(Plan record);

    Plan selectById(Integer id);

    int update(Plan record);

    Page queryUserPage(Map params, Page page);
}
