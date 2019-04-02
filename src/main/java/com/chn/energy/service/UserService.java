package com.chn.energy.service;

import com.chn.energy.model.User;
import com.chn.energy.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouxianwu on 2019/3/27.
 */
public interface UserService {
    int delete(Integer id);

    int insert(User record);

    User selectById(Integer id);

    User selectByName(String name);

    int update(User record);

    Page queryUserPage(Map params,Page page);
}
