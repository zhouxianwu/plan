package com.chn.energy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chn.energy.common.OperateEnum;
import com.chn.energy.common.PlanStatusEnum;
import com.chn.energy.common.RoleEnum;
import com.chn.energy.model.Dept;
import com.chn.energy.model.Plan;
import com.chn.energy.model.PlanOperate;
import com.chn.energy.model.User;
import com.chn.energy.service.DeptService;
import com.chn.energy.service.PlanService;
import com.chn.energy.service.UserService;
import com.chn.energy.util.DateUtil;
import com.chn.energy.util.Page;
import com.chn.energy.util.StateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

/**
 * Created by zhouxianwu on 2019/3/28.
 */
@Controller
@RequestMapping("/plan/")
public class PlanController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private PlanService planService;
    @Autowired
    private StateMachine stateMachine;

    @RequestMapping(value="toAdd.do",method = RequestMethod.GET)
    public String toAdd(ModelMap modelMap){
        modelMap.addAttribute("message","toAdd");
        return "planAdd";
    }

    @RequestMapping(value = "getAllStatus.do")
    public void getAllStatus(HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        JSONArray array = new JSONArray();
        for (PlanStatusEnum statusEnum:PlanStatusEnum.class.getEnumConstants()){
            JSONObject object = new JSONObject();
            object.put("id",statusEnum.getId());
            object.put("name",statusEnum.getName());
            array.add(object);
        }
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(array));
    }

    @RequestMapping(value = "getDepts.do")
    public void getDepts(HttpServletResponse response) throws Exception{
        List<Dept> depts = deptService.getAllDept();
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(depts));
    }

    @RequestMapping(value = "savePlan.do", method = RequestMethod.POST)
    public String savePlan(ModelMap modelMap, HttpServletRequest request) throws ParseException {
        User user = (User)request.getSession().getAttribute("loginedUser");
        Plan plan = new Plan();
        plan.setPlanName(request.getParameter("planName"));
        plan.setPlanContent(request.getParameter("planContent"));
        plan.setCreator(user.getId());
        plan.setDeptId(Integer.valueOf(request.getParameter("deptId")));
        plan.setUrgency(Integer.valueOf(request.getParameter("urgency")));
        plan.setStatus(1);
        plan.setStartTime(request.getParameter("startTime"));
        plan.setEndTime(request.getParameter("endTime"));
        plan.setCreateTime(DateUtil.date2Str(new Date()));
        plan.setUpdateTime(DateUtil.date2Str(new Date()));
        planService.insert(plan);
        return "redirect:planList.do";
    }

    @RequestMapping(value="planList.do")
    public String planList(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        Map params = new HashMap();
        params.put("planName",request.getParameter("planName"));
        params.put("deptId",request.getParameter("deptId"));
        params.put("creatorName",request.getParameter("creatorName"));
        params.put("urgency",request.getParameter("urgency"));
        params.put("status", request.getParameter("status"));
        params.put("startETime", request.getParameter("startETime"));
        params.put("endETime", request.getParameter("endETime"));
        int current = null==request.getParameter("currPage")?1:Integer.parseInt(request.getParameter("currPage"));
        Page page = new Page();
        page.setBegin(current);
        page = planService.queryUserPage(params, page);
        modelMap.addAttribute("pageInfo", page);
        modelMap.addAttribute("params", params);
        return "planList";
    }

    @RequestMapping(value="toUpdate.do")
    public String toUpdate(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Plan plan = planService.selectById(id);
        if (plan.getStatus() != PlanStatusEnum.CREATE.getId()){
            modelMap.addAttribute("error", "已审批通过的计划不能修改！");
            return "error";
        }
        modelMap.addAttribute("plan", plan);
        return "planUpdate";
    }

    @RequestMapping(value="update.do")
    public String update(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Plan currentPlan = planService.selectById(id);
        if (currentPlan.getStatus() != PlanStatusEnum.CREATE.getId()){
            modelMap.addAttribute("error", "已审批通过的计划不能修改！");
            return "error";
        }
        Plan plan = new Plan();
        plan.setPlanId(id);
        plan.setPlanName(request.getParameter("planName"));
        plan.setPlanContent(request.getParameter("planContent"));
        plan.setStartTime(request.getParameter("startTime"));
        plan.setEndTime(request.getParameter("endTime"));
        plan.setUpdateTime(DateUtil.date2Str(new Date()));
        planService.update(plan);
        return "redirect:planList.do";
    }

    @RequestMapping(value="detail.do")
    public String detail(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = (User)request.getSession().getAttribute("loginedUser");
        Plan plan = planService.selectById(id);
        modelMap.addAttribute("plan", plan);
        modelMap.addAttribute("operateList",stateMachine.getOperateList(user,plan));
        return "planDetail";
    }

    @RequestMapping(value="delete.do")
    public void delete(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Plan currentPlan = planService.selectById(id);
            if (currentPlan.getStatus() != PlanStatusEnum.CREATE.getId()){
                message =  "已审批通过的计划不能删除！";
            }else {
                planService.delete(id);
            }
        }catch (Exception e){
            message = "操作失败,服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 主任通过计划
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="passPlan.do")
    public void passPlan(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.DIRECTOR.getId()){
                message =  "对不起，只有主任有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.APPROVED.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 申请验收
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="applyCheck.do")
    public void applyCheck(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.DIRECTOR.getId()){
                message =  "对不起，只有主任有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.WAITINGCHECK.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 通过验收
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="passCheck.do")
    public void passCheck(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.LEADER.getId()){
                message =  "对不起，只有领导有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.CHECKSUCCESS.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 驳回验收
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="rejectCheck.do")
    public void rejectCheck(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.LEADER.getId()){
                message =  "对不起，只有领导有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.CHECKFAIL.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 申请作废
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="applyCancel.do")
    public void applyCancel(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.DIRECTOR.getId()){
                message =  "对不起，只有主任有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.WAITINGCANCEL.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 通过作废
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="passCancel.do")
    public void passCancel(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.LEADER.getId()){
                message =  "对不起，只有领导有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.CANCEL.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 申请延期
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="applyDelay.do")
    public void applyDelay(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.DIRECTOR.getId()){
                message =  "对不起，只有主任有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.WAITINGDELAY.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }

    /**
     * 通过延期
     * @param modelMap
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="passDelay.do")
    public void passDelay(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        request.setCharacterEncoding("utf-8");
        try{
            User loginedUser = (User)request.getSession().getAttribute("loginedUser");
            if (loginedUser.getRole()!= RoleEnum.LEADER.getId()){
                message =  "对不起，只有领导有该权限";
            }else {
                Integer planId = Integer.valueOf(request.getParameter("id"));
                Plan plan = new Plan();
                plan.setPlanId(planId);
                plan.setStatus(PlanStatusEnum.DELAY.getId());
                planService.update(plan);
            }
        }catch (Exception e){
            message = "操作失败，服务器异常";
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(message));
    }
}
