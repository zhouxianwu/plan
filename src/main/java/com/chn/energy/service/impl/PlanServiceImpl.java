package com.chn.energy.service.impl;

import com.chn.energy.common.PlanStatusEnum;
import com.chn.energy.common.UrgencyEnum;
import com.chn.energy.dao.PlanMapper;
import com.chn.energy.model.Plan;
import com.chn.energy.service.PlanService;
import com.chn.energy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouxianwu on 2019/3/30.
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public int delete(Integer id) {
        return planMapper.delete(id);
    }

    @Override
    public int insert(Plan record) {
        return planMapper.insert(record);
    }

    @Override
    public Plan selectById(Integer id) {
        Plan plan =  planMapper.selectById(id);
        if (null != plan){
            fillPlan(plan);
        }
        return plan;
    }

    @Override
    public int update(Plan record) {
        return planMapper.update(record);
    }

    @Override
    public Page queryUserPage(Map params, Page page) {
        int count = planMapper.count(params);
        params.put("begin",page.getBegin());
        params.put("length",page.getLength());
        List<Plan> plans = planMapper.queryList(params);
        for (Plan plan:plans){
            fillPlan(plan);
        }
        page.setCount(count);
        page.setResult(plans);
        page.setPageHtml(page);
        return page;
    }

    private void fillPlan(Plan plan){
        plan.setUrgencyDesc(UrgencyEnum.codeOf(plan.getUrgency()).getName());
        plan.setStatusDesc(PlanStatusEnum.codeOf(plan.getStatus()).getName());
    }
}
