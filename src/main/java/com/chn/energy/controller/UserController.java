package com.chn.energy.controller;

import com.alibaba.fastjson.JSON;
import com.chn.energy.common.RoleEnum;
import com.chn.energy.model.Dept;
import com.chn.energy.model.User;
import com.chn.energy.service.DeptService;
import com.chn.energy.service.UserService;
import com.chn.energy.util.DateUtil;
import com.chn.energy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxianwu on 2019/3/28.
 */
@Controller
@RequestMapping("/user/")
public class UserController  {

    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="toUserAdd.do",method = RequestMethod.GET)
    public String toUserAdd(ModelMap modelMap){
        modelMap.addAttribute("message","toUserAdd");
        return "userAdd";
    }

    @RequestMapping(value = "getDepts.do")
    public void getDepts(HttpServletResponse response) throws Exception{
        List<Dept> depts = deptService.getAllDept();
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(depts));
    }

    @RequestMapping(value = "queryUserByName.do")
    public void queryUserByName(HttpServletRequest request,HttpServletResponse response) throws Exception{
        User user = userService.selectByName(request.getParameter("userName"));
        response.setCharacterEncoding("utf-8");
        PrintWriter printer = response.getWriter();
        printer.write(JSON.toJSONString(user));
    }

    @RequestMapping(value = "saveUser.do", method = RequestMethod.POST)
    public String saveUser(ModelMap modelMap, HttpServletRequest request) {
        User user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setPassWord(request.getParameter("passWord"));
        user.setRealName(request.getParameter("realName"));
        user.setDeptId(Integer.valueOf(request.getParameter("deptId")));
        user.setRole(Integer.valueOf(request.getParameter("role")));
        user.setTelphone(request.getParameter("telphone"));
        user.setEmail(request.getParameter("email"));
        user.setValid(0);
        user.setCreateDate(DateUtil.date2Str(new Date()));
        user.setUpdateDate(DateUtil.date2Str(new Date()));
        userService.insert(user);
        return "redirect:/login/toLogin.do";
    }

    @RequestMapping(value="userList.do")
    public String userList(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        User user = (User)request.getSession().getAttribute("loginedUser");
        if (user.getRole()!= RoleEnum.ADMIN.getId()){
            modelMap.addAttribute("error", "对不起，只有管理员有该菜单权限");
            return "error";
        }
        Map params = new HashMap();
        params.put("userName",request.getParameter("userName"));
        params.put("realName",request.getParameter("realName"));
        params.put("deptId",request.getParameter("deptId"));
        params.put("valid", request.getParameter("valid"));
        int current = null==request.getParameter("currPage")?1:Integer.parseInt(request.getParameter("currPage"));
        Page page = new Page();
        page.setBegin(current);
        page = userService.queryUserPage(params, page);
        modelMap.addAttribute("pageInfo", page);
        modelMap.addAttribute("params", params);
        return "userList";
    }

    @RequestMapping(value="toUpdate.do")
    public String toUpdate(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        User user = (User)request.getSession().getAttribute("loginedUser");
        User userInfo = userService.selectById(user.getId());
        modelMap.addAttribute("user", userInfo);
        return "userUpdate";
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
     public String update(ModelMap modelMap, HttpServletRequest request) {
        User user = new User();
        user.setPassWord(request.getParameter("passWord"));
        user.setDeptId(Integer.valueOf(request.getParameter("deptId")));
        user.setTelphone(request.getParameter("telphone"));
        user.setEmail(request.getParameter("email"));
        user.setUpdateDate(DateUtil.date2Str(new Date()));
        userService.update(user);
        return "redirect:toLogin.do";
    }

    @RequestMapping(value="detail.do")
    public String detail(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        User user = (User)request.getSession().getAttribute("loginedUser");
        User userInfo = userService.selectById(user.getId());
        modelMap.addAttribute("user", userInfo);
        return "userDetail";
    }

    @RequestMapping(value="checkUser.do")
    public String checkUser(ModelMap modelMap,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        User loginedUser = (User)request.getSession().getAttribute("loginedUser");
        if (loginedUser.getRole()!= RoleEnum.ADMIN.getId()){
            modelMap.addAttribute("error", "对不起，只有管理员有该菜单权限");
            return "error";
        }
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = new User();
        user.setId(id);
        user.setValid(1);
        userService.update(user);
        return "redirect:userList.do";
    }
}
