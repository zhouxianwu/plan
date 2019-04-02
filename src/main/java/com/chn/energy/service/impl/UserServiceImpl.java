package com.chn.energy.service.impl;

import com.chn.energy.common.RoleEnum;
import com.chn.energy.dao.UserMapper;
import com.chn.energy.model.User;
import com.chn.energy.service.UserService;
import com.chn.energy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouxianwu on 2019/3/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectById(Integer id) {
        User user =  userMapper.selectById(id);
        if (null == user){
            return null;
        }
        fillUser(user);
        return user;
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public int update(User record) {
        return userMapper.update(record);
    }

    @Override
    public Page queryUserPage(Map params, Page page) {
        int count = userMapper.count(params);
        params.put("begin",page.getBegin());
        params.put("length",page.getLength());
        List<User> users = userMapper.queryList(params);
        for (User user:users){
            fillUser(user);
        }
        page.setCount(count);
        page.setResult(users);
        page.setPageHtml(page);
        return page;
    }

    private void fillUser(User user){
        user.setRoleName(RoleEnum.codeOf(user.getRole()).getName());
    }
}
