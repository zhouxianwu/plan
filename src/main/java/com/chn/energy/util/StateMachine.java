package com.chn.energy.util;

import com.chn.energy.common.OperateEnum;
import com.chn.energy.common.PlanStatusEnum;
import com.chn.energy.common.RoleEnum;
import com.chn.energy.model.Plan;
import com.chn.energy.model.PlanOperate;
import com.chn.energy.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划状态变更处理器
 * Created by zhouxianwu on 2019/4/1.
 */
@Component
public class StateMachine {
    /**
     * 获取计划按钮操作
     * @param user
     * @param plan
     * @return
     */
    public  List<PlanOperate> getOperateList(User user,Plan plan){
        List<PlanOperate> planOperates = new ArrayList<PlanOperate>();
        //新建状态
        if (plan.getStatus() == PlanStatusEnum.CREATE.getId()){
            //计划员
            if (user.getRole() == RoleEnum.PLAN.getId() && user.getId() == plan.getCreator()){
                //修改
                planOperates.add(buildPlanOperate(OperateEnum.UPDATE, plan));
                //删除
                planOperates.add(buildPlanOperate(OperateEnum.DELETE, plan));
            }
            //主任
            if (user.getRole() == RoleEnum.DIRECTOR.getId()&& user.getDeptId() == plan.getDeptId()){
                planOperates.add(buildPlanOperate(OperateEnum.PASS, plan));
            }
        }
        //进行中状态
        if (plan.getStatus() == PlanStatusEnum.APPROVED.getId()){
            //主任
            if (user.getRole() == RoleEnum.DIRECTOR.getId()&& user.getDeptId() == plan.getDeptId()){
                //申请验收
                planOperates.add(buildPlanOperate(OperateEnum.APPLYCHECK, plan));
                //申请延期
                planOperates.add(buildPlanOperate(OperateEnum.APPLYDELAY, plan));
                //申请作废
                planOperates.add(buildPlanOperate(OperateEnum.APPLYCANCEL, plan));
            }
        }
        //申请验收状态
        if (plan.getStatus() == PlanStatusEnum.WAITINGCHECK.getId()){
            //领导
            if (user.getRole() == RoleEnum.LEADER.getId()){
                //通过验收
                planOperates.add(buildPlanOperate(OperateEnum.PASSCHECK, plan));
                //驳回验收
                planOperates.add(buildPlanOperate(OperateEnum.REJECTCHECK, plan));
            }
        }
        //申请延期状态
        if (plan.getStatus() == PlanStatusEnum.WAITINGDELAY.getId()){
            //领导
            if (user.getRole() == RoleEnum.LEADER.getId()){
                //通过延期
                planOperates.add(buildPlanOperate(OperateEnum.PASSDELAY, plan));
            }
        }
        //申请作废状态
        if (plan.getStatus() == PlanStatusEnum.WAITINGCANCEL.getId()){
            //领导
            if (user.getRole() == RoleEnum.LEADER.getId()){
                //通过作废申请
                planOperates.add(buildPlanOperate(OperateEnum.PASSCANCEL, plan));
            }
        }
        return planOperates;
    }

    private  PlanOperate buildPlanOperate(OperateEnum operateEnum, Plan plan){
        PlanOperate operate = new PlanOperate();
        operate.setCode(operateEnum.getCode());
        operate.setName(operateEnum.getName());
        operate.setUrl(operateEnum.getUrlPrefix() + plan.getPlanId());
        operate.setType(operateEnum.getType());
        return operate;
    }
}
