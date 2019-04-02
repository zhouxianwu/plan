package com.chn.energy.controller;

import com.chn.energy.model.User;
import com.chn.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouxianwu on 2019/3/27.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="toLogin.do",method = RequestMethod.GET)
    public String toLogin(ModelMap modelMap){
        modelMap.addAttribute("message","login");
        return "/login";
    }

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public  String login(ModelMap modelMap,@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request){
        User user = userService.selectByName(username);
        if(null!=user && user.getValid() == 1&& password.equals(user.getPassWord())){
            modelMap.addAttribute("username",username);
            request.getSession().setAttribute("loginedUser",user);
            return "redirect:/plan/planList.do";
        }
        if (null == user){
            modelMap.addAttribute("message","NoUser");
            return "login";
        }
        if (!password.equals(user.getPassWord())){
            modelMap.addAttribute("message","PassError");
            return "login";
        }
        if (user.getValid() == 0){
            modelMap.addAttribute("message","NoCheck");
            return "login";
        }
        modelMap.addAttribute("message","false");
        return "login";
    }

    @RequestMapping(value = "loginOut.do",method = RequestMethod.GET)
    public  String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("loginedUser");
        return "login";
    }
}
