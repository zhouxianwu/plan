package com.chn.energy.job;

import com.chn.energy.common.PlanStatusEnum;
import com.chn.energy.dao.PlanMapper;
import com.chn.energy.dao.UserMapper;
import com.chn.energy.model.Plan;
import com.chn.energy.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhouxianwu on 2019/4/2.
 */
@Service
public class PlanStatusJob {
    @Autowired
    private PlanMapper planMapper;

    public void scanTimeOutPlan() throws ParseException {
        List<Plan> planList = planMapper.queryList(new HashMap());
        if (null == planList || planList.size() == 0) return;
        for (Plan plan:planList){
            if (plan.getStatus() == PlanStatusEnum.CREATE.getId() ||
                    plan.getStatus() == PlanStatusEnum.APPROVED.getId()){
                if (DateUtil.str2Date(plan.getEndTime()).before(new Date())){
                    System.out.println("任务过期，任务id："+plan.getPlanId());
                    Plan record = new Plan();
                    record.setPlanId(plan.getPlanId());
                    record.setStatus(PlanStatusEnum.TIMEOUT.getId());
                    planMapper.update(record);
                }
            }
        }
    }
}
